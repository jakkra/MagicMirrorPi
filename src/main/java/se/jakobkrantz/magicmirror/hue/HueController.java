package se.jakobkrantz.magicmirror.hue;

/**
 * Created by jakkra on 2016-03-19.
 */

import com.philips.lighting.hue.sdk.*;
import com.philips.lighting.model.*;

import java.util.List;

public class HueController {

    private PHHueSDK phHueSDK;
    private static final int MAX_HUE = 65535;
    private boolean isConnected = false;
    private int currentBrightness;
    private String[] lights = {"BOTH", "BED", "HALLWAY"};
    private int lightIndex = 0;
    private boolean shouldPulse = false;
    private int savedBrightness = 254;


    public HueController() {
        this.phHueSDK = PHHueSDK.getInstance();
        HueProperties.loadProperties();
        phHueSDK.setAppName("MagicMirror");
        phHueSDK.setDeviceName("RaspberryPi");
        phHueSDK.getNotificationManager().registerSDKListener(getListener());
        currentBrightness = 0;

    }

    public void findBridges() {
        if (connectToLastKnownAccessPoint()) {
            System.out.println("Using old username and pass to connect to previous bridge");
        } else {
            System.out.println("Looking for bridges");
            PHBridgeSearchManager sm = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
            sm.search(true, true);
        }

    }

    private PHSDKListener listener = new PHSDKListener() {

        @Override
        public void onAccessPointsFound(List<PHAccessPoint> accessPointsList) {
            if (accessPointsList.size() > 0) {
                System.out.println(accessPointsList.get(0).getIpAddress());
                System.out.println(accessPointsList.get(0).getUsername());
                System.out.println(accessPointsList.get(0).getBridgeId());
                phHueSDK.connect(accessPointsList.get(0));
            } else {
                System.out.println("Could not find any bridges");
            }
        }

        @Override
        public void onAuthenticationRequired(PHAccessPoint accessPoint) {
            // Start the Pushlink Authentication.
            if (connectToLastKnownAccessPoint()) {
                System.out.println("Using old username and pass to connect to bridge");
            } else {
                System.out.println("You now need to click the big button on your bridge!");
                phHueSDK.startPushlinkAuthentication(accessPoint);
            }
        }

        @Override
        public void onBridgeConnected(PHBridge bridge, String username) {
            System.out.println("Bridge connected!");
            phHueSDK.setSelectedBridge(bridge);
            phHueSDK.enableHeartbeat(bridge, PHHueSDK.HB_INTERVAL);
            String lastIpAddress = bridge.getResourceCache().getBridgeConfiguration().getIpAddress();
            HueProperties.storeUsername(username);
            HueProperties.storeLastIPAddress(lastIpAddress);
            HueProperties.saveProperties();

            isConnected = true;
        }

        @Override
        public void onCacheUpdated(List cacheNotificationsList, PHBridge bridge) {
            if (cacheNotificationsList.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {
                System.out.println("Lights Cache Updated ");
            }
        }

        @Override
        public void onConnectionLost(PHAccessPoint arg0) {
            System.out.println("Disconnected from the bridge?? WHY!!?");
        }

        @Override
        public void onConnectionResumed(PHBridge arg0) {
        }

        @Override
        public void onError(int code, final String message) {
            System.out.println(code + " -- " + message);

            if (code == PHHueError.BRIDGE_NOT_RESPONDING) {
                System.out.println("bridge not responding");

            } else if (code == PHMessageType.PUSHLINK_BUTTON_NOT_PRESSED) {
                System.out.println("pushlink not pressed");

            } else if (code == PHMessageType.PUSHLINK_AUTHENTICATION_FAILED) {
                System.out.println("auth failed");

            } else if (code == PHMessageType.BRIDGE_NOT_FOUND) {
                System.out.println("bridge not found");
            } else {
                System.out.println("idk what happend");
            }
        }

        @Override
        public void onParsingErrors(List<PHHueParsingError> parsingErrorsList) {
            for (PHHueParsingError parsingError : parsingErrorsList) {
                System.out.println("ParsingError : " + parsingError.getMessage());
            }
        }
    };

    public PHSDKListener getListener() {
        return listener;
    }

    public void setListener(PHSDKListener listener) {
        this.listener = listener;
    }

    public void toggleAllLights(boolean on) {
        currentBrightness = on ? 254 : 0;
        if (!isConnected) {
            System.out.println("Not connected to bridge yet! Wait a second :)");
            return;
        }
        System.out.println("All lights switch");
        PHBridge bridge = phHueSDK.getSelectedBridge();
        PHBridgeResourcesCache cache = bridge.getResourceCache();

        List<PHLight> allLights = cache.getAllLights();
        for (PHLight light : allLights) {
            System.out.println(light.getName());
            PHLightState lightState = new PHLightState();
            lightState.setOn(on);
            lightState.setBrightness(savedBrightness);
            if (lights[lightIndex].equals(light.getName()) || lights[lightIndex].equals("BOTH")) {
                bridge.updateLightState(light, lightState);
            }
        }
    }

    public void setBrightness(int brightness) {
        if (!isConnected) {
            System.out.println("Not connected to bridge yet! Wait a second :)");
            return;
        }
        currentBrightness = brightness;
        PHBridge bridge = phHueSDK.getSelectedBridge();
        PHBridgeResourcesCache cache = bridge.getResourceCache();

        List<PHLight> allLights = cache.getAllLights();

        PHLightState lightState = new PHLightState();
        lightState.setBrightness(brightness);
        for (PHLight light : allLights) {
            if (lights[lightIndex].equals(light.getName()) || lights[lightIndex].equals("BOTH")) {
                bridge.updateLightState(light, lightState);
            }
        }
    }

    public void pulseLights() {
        if (!isConnected) {
            System.out.println("Not connected to bridge yet! Wait a second :)");
            return;
        }
        PHBridge bridge = phHueSDK.getSelectedBridge();
        PHBridgeResourcesCache cache = bridge.getResourceCache();

        List<PHLight> allLights = cache.getAllLights();

        PHLightState lightState = new PHLightState();
        lightState.setTransitionTime(30);
        lightState.setBrightness(254);
        shouldPulse = true;
        new Thread(() -> {
            boolean isOn = true;

            while (shouldPulse) {
                for (PHLight light : allLights) {
                    if (lights[lightIndex].equals(light.getName()) || lights[lightIndex].equals("BOTH")) {
                        lightState.setOn(isOn);
                        bridge.updateLightState(light, lightState);
                    }
                }
                isOn = !isOn;
                System.out.println("---------------DISCO IS ON----------------------");

                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("---------------Stopped pulsing-----------------------");
        }).start();
    }

    public void stopPulsing() {
        shouldPulse = false;
    }


    public void changeBrightness(boolean increase) {
        if (!isConnected) {
            System.out.println("Not connected to bridge yet! Wait a second :)");
            return;
        }
        PHBridge bridge = phHueSDK.getSelectedBridge();
        PHBridgeResourcesCache cache = bridge.getResourceCache();

        List<PHLight> allLights = cache.getAllLights();

        PHLightState lightState = new PHLightState();
        if (increase) {
            currentBrightness += 0.25 * 254;
        } else {
            currentBrightness -= 0.25 * 254;
        }
        if (currentBrightness > 254) {
            currentBrightness = 254;
        } else if (currentBrightness < 0) {
            currentBrightness = 0;
        }
        savedBrightness = currentBrightness;
        System.out.println("Setting brightness: " + currentBrightness);
        lightState.setBrightness(currentBrightness);
        for (PHLight light : allLights) {
            if (lights[lightIndex].equals(light.getName()) || lights[lightIndex].equals("BOTH")) {
                bridge.updateLightState(light, lightState);
            }
        }
    }


    /**
     * Connect to the last known access point.
     * This method is triggered by the Connect to Bridge button but it can equally be used to automatically connect to a bridge.
     */
    private boolean connectToLastKnownAccessPoint() {
        String username = HueProperties.getUsername();
        String lastIpAddress = HueProperties.getLastConnectedIP();

        if (username == null || lastIpAddress == null) {
            System.out.println("Missing Last Username or Last IP.  Last known connection not found.");
            return false;
        }
        PHAccessPoint accessPoint = new PHAccessPoint();
        accessPoint.setIpAddress(lastIpAddress);
        accessPoint.setUsername(username);
        phHueSDK.connect(accessPoint);
        return true;
    }

    public String changeLightDestination() {
        lightIndex++;
        lightIndex %= 3;
        return "SELECTED LIGHT: " + lights[lightIndex];
    }

    public String changeLightDestination(String loc) {
        switch (loc) {
            case "BOTH":
                lightIndex = 0;
                break;
            case "BED":
                lightIndex = 1;
                break;
            case "HALLWAY":
                lightIndex = 2;
                break;
        }
        return "SELECTED LIGHT: " + lights[lightIndex];
    }

}

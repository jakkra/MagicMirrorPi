package se.jakobkrantz.magic.hue;

/**
 * Created by jakkra on 2016-03-19.
 */

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHHueParsingError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;

public class HueController {

    private PHHueSDK phHueSDK;
    private static final int MAX_HUE = 65535;
    private boolean isConnected = false;

    public HueController() {
        this.phHueSDK = PHHueSDK.getInstance();
        HueProperties.loadProperties();
        phHueSDK.setAppName("MagicMirror");
        phHueSDK.setDeviceName("RaspberryPi");
        phHueSDK.getNotificationManager().registerSDKListener(getListener());

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
                System.out.println("Could not find any brdiges");
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
        System.out.println("All lights");
        if(!isConnected){
            System.out.println("Not connected to bridge yet! Wait a second :)");
            return;
        }
        System.out.println("All lights switch");
        PHBridge bridge = phHueSDK.getSelectedBridge();
        PHBridgeResourcesCache cache = bridge.getResourceCache();

        List<PHLight> allLights = cache.getAllLights();

        for (PHLight light : allLights) {
            PHLightState lightState = new PHLightState();
            lightState.setOn(on);
            bridge.updateLightState(light, lightState); // If no bridge response is required then use this simpler form.
        }
    }


    /**
     * Connect to the last known access point.
     * This method is triggered by the Connect to Bridge button but it can equally be used to automatically connect to a bridge.
     */
    public boolean connectToLastKnownAccessPoint() {
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
}

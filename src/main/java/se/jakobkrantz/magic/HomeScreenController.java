package se.jakobkrantz.magic;

import se.jakobkrantz.magic.downloaders.SearchJourneysTask;
import se.jakobkrantz.magic.downloaders.SearchStationsTask;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.*;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.github.fedy2.weather.data.unit.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;
import se.jakobkrantz.magic.hue.HueController;
import se.jakobkrantz.magic.sensors.PirMotionDetector;
import se.jakobkrantz.magic.sensors.TemperatureSensorReader;
import se.jakobkrantz.magic.speech.SpeechRecognizer;
import se.jakobkrantz.magic.util.Greetings;
import se.jakobkrantz.magic.util.TemperatureLogger;
import se.jakobkrantz.magic.util.WeatherConditionCodes;
import se.jakobkrantz.magic.skanetrafikenAPI.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeScreenController implements Initializable {
    @FXML
    private Label nextJourneyLabel;

    @FXML
    private Label timeToDepLabel;

    @FXML
    private Label weatherFontLabel;

    @FXML
    private Label currentWeatherLabel;

    @FXML
    private Label dateDayLabel;

    @FXML
    private Label greetingsLabel;

    @FXML
    private Label clockTimeLabel;

    @FXML
    private Label newsHeadlinesLabel;

    @FXML
    private Label day1, day2, day3, day4, day5;

    @FXML
    private Label text1, text2, text3, text4, text5;

    @FXML
    private Label tempMax1, tempMax2, tempMax3, tempMax4, tempMax5;

    @FXML
    private Label tempLow1, tempLow2, tempLow3, tempLow4, tempLow5;

    @FXML
    private Label insideTemperatureLabel;

    private List<Label> dayLabels;
    private List<Label> lowLabels;
    private List<Label> highLabels;
    private List<Label> textWeatherLabels;

    private int startStationId, endStationId;

    private HueController hue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dayLabels = Arrays.asList(day1, day2, day3, day4, day5);
        lowLabels = Arrays.asList(tempLow1, tempLow2, tempLow3, tempLow4, tempLow5);
        highLabels = Arrays.asList(tempMax1, tempMax2, tempMax3, tempMax4, tempMax5);
        textWeatherLabels = Arrays.asList(text1, text2, text3, text4, text5);
        setFont(dayLabels, "Heiti SC");
        setFont(lowLabels, "Heiti SC");
        setFont(highLabels, "Heiti SC");
        setFont(textWeatherLabels, "Heiti SC");

        setWeatherFont(weatherFontLabel);
        greetingsLabel.setText("Greetings stranger!");

        setupDepartureUpdatesAndTemperature(30);
        updateWeatherForecastAndNews(10);
        setupClockUpdates(5);
        initMotionDetector();
        startVoiceCommands();
        hue = new HueController();
        hue.findBridges();
    }

    private void startVoiceCommands() {
        SpeechRecognizer speechRecognizer = new SpeechRecognizer();
        speechRecognizer.addListener(command -> {
            System.out.println("Got voice command: " + command);
            if (command.equals("PUT LIGHTS ON ")) {
                hue.toggleAllLights(true);
            } else if (command.equals("TURN LIGHTS OFF ")) {
                hue.toggleAllLights(false);
                hue.stopPulsing();
            } else if (command.equals("FIFTY FIFTY LIGHTS ")){
                System.out.println("Dimming lights");
                hue.dimAllLights(125);
            } else if(command.equals("UP THE BRIGHTNESS ")){
                hue.changeBrightness(true);
            } else if(command.equals("DECREASE BRIGHTNESS ")){
                hue.changeBrightness(false);
            } else if(command.equals("TIME TO SLEEP ")){
                hue.dimAllLights(30);
            } else if(command.equals("CHANGE LIGHT ")){
                hue.changeLightDestination();
            } else if(command.equals("START DISCO ")){
                hue.pulseLights();
            } else if(command.equals("STOP PULSING ")){
                hue.stopPulsing();
            }

            Platform.runLater(() -> {
                greetingsLabel.setText(command);
            });


        });
        speechRecognizer.start();
    }

    /**
     * @param labels   label to set font on
     * @param fontName name of the font
     */
    private void setFont(List<Label> labels, String fontName) {
        for (Label l : labels) {
            l.setFont(Font.font(fontName, 22));
        }
    }


    /**
     * Sets up and initializes the PIR motion detector
     */
    private void initMotionDetector() {
        new Thread(() -> {
            PirMotionDetector motionDetector = new PirMotionDetector();
            motionDetector.setMotionDetectionListener(
                    new PirMotionDetector.MotionDetectionListener() {
                        @Override
                        public void onMotionDetected() {
                            Platform.runLater(() -> {
                                System.out.println("Motion Detected!");
                                showMessageDependingOnTime();
                            });
                        }

                        @Override
                        public void onMotionStopped() {
                            Platform.runLater(() -> {
                                System.out.println("All is quiet...");
                            });
                        }
                    }
            );
            motionDetector.startDetecting();
        }
        ).start();
    }

    /**
     * Updates the greeting Label, depending on time of day
     */
    private void showMessageDependingOnTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        Random rand;
        if (hour >= 5 && hour < 12) {
            rand = new Random();
            greetingsLabel.setText(Greetings.morning()[rand.nextInt(Greetings.morning().length)]);
        } else if (hour >= 12 && hour < 18) {
            rand = new Random();
            greetingsLabel.setText(Greetings.afternoon()[rand.nextInt(Greetings.afternoon().length)]);
        } else if (hour >= 18 || (hour >= 0 && hour < 5)) {
            rand = new Random();
            greetingsLabel.setText(Greetings.evening()[rand.nextInt(Greetings.evening().length)]);
        } else {
            greetingsLabel.setText("Something wrong, hour is: " + hour);
        }
    }


    /**
     * Sets the weather font from https://erikflowers.github.io/weather-icons/
     * to a Label
     *
     * @param weatherLabel
     */
    private void setWeatherFont(Label weatherLabel) {
        Font f = null;
        try {
            URL url = getClass().getResource("/fonts/weathericons-regular-webfont.ttf");
            f = Font.loadFont(url.openStream(), 120);
        } catch (IOException e) {
            e.printStackTrace();
        }
        weatherLabel.setFont(f);
    }

    /**
     * Sets up how often the digital clock should update
     */
    private void setupClockUpdates(int refreshRate) {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(refreshRate), event -> {
            Calendar now = Calendar.getInstance();
            clockTimeLabel.setText(new SimpleDateFormat("HH:mm").format(now.getTime()));
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    /**
     * Pulls fresh data from Yahoo weather and updates the GUI
     *
     * @param updateInterval in minutes
     */
    private void updateWeatherForecastAndNews(int updateInterval) {
        Runnable updateWeather = () -> {
            System.out.println("Updating weather");
            YahooWeatherService service;
            try {
                service = new YahooWeatherService();
                Channel channel = service.getForecast("898091", DegreeUnit.CELSIUS);
                List<Forecast> forecasts = channel.getItem().getForecasts();
                //StringBuilder sb = updateNewsHeadlines();


                Platform.runLater(() -> {
                    Forecast tf;
                    for (int i = 0; i < forecasts.size() && i < 5; i++) {
                        tf = forecasts.get(i);
                        dayLabels.get(i).setText(tf.getDay().toString());
                        lowLabels.get(i).setText(tf.getLow() + "°C");
                        highLabels.get(i).setText(tf.getHigh() + "°C");
                        textWeatherLabels.get(i).setText(tf.getText());
                    }
                    Condition cond = channel.getItem().getCondition();
                    //TODO Check not null
                    Wind wind = channel.getWind();
                    int feelsLike = wind.getChill();
                    float windSpeed = wind.getSpeed();
                    int windDirection = wind.getDirection();
                    System.out.println("Feels like: " + feelsLike + " wind speed: " + windSpeed + " direction: " + windDirection);
                    Astronomy as = channel.getAstronomy();
                    Time sunRise = as.getSunrise();
                    Time sunset = as.getSunset();
                    System.out.println("Sunrise: " + sunRise.toString() + " Sunset: " + sunset.toString());

                    Atmosphere atm = channel.getAtmosphere();
                    float viability = atm.getVisibility();
                    System.out.println("Visability: " + viability);
                    System.out.println(atm.getRising().toString());

                    currentWeatherLabel.setText(cond.getTemp() + "\u2103" + "\n" + "feels like " + feelsLike + "\u2103" + "\n" + cond.getText() + "\nWind speed " + Math.round(windSpeed / 3.6) + "m/s");

                    weatherFontLabel.setText(WeatherConditionCodes.fromInt(cond.getCode()).toString());
                    //newsHeadlinesLabel.setText(sb.toString());

                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(updateWeather, 0, updateInterval, TimeUnit.MINUTES);
    }

    /**
     * Pulls fresh news from Yahoo RSS feed and updates the GUI
     */
    private StringBuilder updateNewsHeadlines() {
        System.out.println("News update");

        YahooNews news = new YahooNews();
        List<String> headlines = news.getLatestHeadlines();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < headlines.size() && i < 6; i++) {
            System.out.println("Headline: " + headlines.get(i));
            sb.append(headlines.get(i).split(" - ")[0] + "\n");
        }
        return sb;
    }

    /**
     * Sets up
     *
     * @param updateInterval in seconds
     */
    private void setupDepartureUpdatesAndTemperature(int updateInterval) {
        System.out.println("Setup departures");
        Runnable updateDepartures = () -> {
            System.out.println("-----------Updating departures----------------");
            SearchStationsTask task = new SearchStationsTask();
            String url = Constants.getSearchStationURL("Värnhem");
            ArrayList<Station> stationsResult = task.download(url);
            Station varnhem = stationsResult.get(0);
            startStationId = varnhem.getStationId();

            task = new SearchStationsTask();
            url = Constants.getSearchStationURL("Lund LTH");
            stationsResult = task.download(url);
            Station lth = stationsResult.get(0);
            endStationId = lth.getStationId();

            String journey = Constants.getURL(varnhem.getStationId(), lth.getStationId(), 5);

            SearchJourneysTask jTask = new SearchJourneysTask();
            final ArrayList<Journey> js = jTask.download(journey);

            final StringBuilder sb = new StringBuilder();
            for (Journey j : js) {
                String depTime = TimeAndDateConverter.timeToDeparture(j.getDepDateTime());
                if (j.deviationType() == RouteLink.EARLY) {
                    sb.append(j.getFirstRouteLineNbr() + "" + depTime + " (" + j.getDeviationDepTime() + " early)\n");
                } else if (j.deviationType() == RouteLink.LATE) {
                    sb.append(j.getFirstRouteLineNbr() + " " + depTime + " (" + j.getDeviationDepTime() + " late)\n");
                } else if (j.deviationType() == RouteLink.IN_TIME) {
                    sb.append(j.getFirstRouteLineNbr() + " " + depTime + " (in time)\n");
                } else {
                    sb.append(j.getFirstRouteLineNbr() + " in " + depTime + "\n");
                }
            }
            System.out.println(sb.toString());
            /*Platform.runLater(() -> {
                nextJourneyLabel.setText("Upcoming departures to " + js.get(0).getEndStation().getStationName() + " in:\n");
                timeToDepLabel.setText(sb.toString());
                dateDayLabel.setText(getDatePrint());

            });
            */
            TemperatureSensorReader temperatureSensorReader = new TemperatureSensorReader();
            double insideTemp = temperatureSensorReader.readTemperatureFirstSensor();
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
            String logMsg = timeStamp + " : " + insideTemp + "°C";
            System.out.println(logMsg);
            TemperatureLogger.appendLog(logMsg);
            Platform.runLater(() -> {
                nextJourneyLabel.setText("Upcoming departures to " + js.get(0).getEndStation().getStationName() + " in:\n");
                timeToDepLabel.setText(sb.toString());
                dateDayLabel.setText(getDatePrint());
                insideTemperatureLabel.setText("Inside " + insideTemp + "°C");

            });

        };

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(updateDepartures, 0, updateInterval, TimeUnit.SECONDS);


    }

    private String getDatePrint() {
        Calendar now = Calendar.getInstance();
        String monthOfYear = new SimpleDateFormat("EEEE d MMMM, y", Locale.ENGLISH).format(now.getTime());
        return monthOfYear;
    }

}




package se.jakobkrantz.magicmirror;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Astronomy;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.Forecast;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.github.fedy2.weather.data.unit.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import se.jakobkrantz.magicmirror.downloaders.SearchJourneysTask;
import se.jakobkrantz.magicmirror.downloaders.SearchStationsTask;
import se.jakobkrantz.magicmirror.hue.HueController;
import se.jakobkrantz.magicmirror.news.NewsWrapper;
import se.jakobkrantz.magicmirror.sensors.PirMotionDetector;
import se.jakobkrantz.magicmirror.skanetrafikenAPI.*;
import se.jakobkrantz.magicmirror.smhi.Forecasts;
import se.jakobkrantz.magicmirror.smhi.HourlyForecast;
import se.jakobkrantz.magicmirror.smhi.SMHIWeatherAPI;
import se.jakobkrantz.magicmirror.smhi.WeatherUtils;
import se.jakobkrantz.magicmirror.speech.SpeechRecognizerGoogle;
import se.jakobkrantz.magicmirror.speech.TextToSpeech;
import se.jakobkrantz.magicmirror.speech.VoiceCommandListener;
import se.jakobkrantz.magicmirror.speech.VoiceParser;
import se.jakobkrantz.magicmirror.util.Greetings;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.xml.bind.JAXBException;
import java.beans.PropertyVetoException;
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
    private ImageView weatherIconView;

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
    @FXML
    private ImageView windDirectionImage;
    @FXML
    private VBox newsContainer;
    @FXML
    private HBox forecastContainer;

    private List<Label> dayLabels;
    private List<Label> lowLabels;
    private List<Label> highLabels;
    private List<Label> textWeatherLabels;

    private int startStationId, endStationId;

    private HueController hue;
    private SMHIWeatherAPI smhiWeatherAPI;
    private YahooWeatherService yahooWeatherService;
    private VoiceParser voiceParser;
    private NewsWrapper news;
    private SpeechRecognizerGoogle recognizerGoogle;

    private TextToSpeech textToSpeech;
    private String nextDeparture = "";


    public HomeScreenController() {

        news = new NewsWrapper();

        smhiWeatherAPI = new SMHIWeatherAPI("13", "55.6");
        try {
            yahooWeatherService = new YahooWeatherService();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        recognizerGoogle = new SpeechRecognizerGoogle();
        voiceParser = new VoiceParser();
        String host = "speech.googleapis.com";
        Integer port = 443;
        Integer sampling = 16000;
        textToSpeech = new TextToSpeech();
        try {
            textToSpeech.init("kevin16");
        } catch (EngineException e) {
            e.printStackTrace();
        } catch (AudioException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }


       /* try {
            recognizeClient = new RecognizeClient(host, port, sampling);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("HomeScreenController.initialize");
        dayLabels = Arrays.asList(day1, day2, day3, day4, day5);
        lowLabels = Arrays.asList(tempLow1, tempLow2, tempLow3, tempLow4, tempLow5);
        highLabels = Arrays.asList(tempMax1, tempMax2, tempMax3, tempMax4, tempMax5);
        textWeatherLabels = Arrays.asList(text1, text2, text3, text4, text5);
        setFont(dayLabels, "Heiti SC");
        setFont(lowLabels, "Heiti SC");
        setFont(highLabels, "Heiti SC");
        setFont(textWeatherLabels, "Heiti SC");
        //setWeatherFont(windDirectionLabel);
        //windDirectionLabel.setText(Character.toString((char) 0xf0b1));


        greetingsLabel.setText("Greetings stranger!");
        setupDepartureUpdatesAndTemperature(30);
        updateWeatherForecastAndNews(10);
        setupClockUpdates(5);
        initMotionDetector();
        hue = new HueController();
        hue.findBridges();


    }

    @FXML
    public void startRecognize() {
        if (recognizerGoogle == null || !recognizerGoogle.isAlive() && !recognizerGoogle.isRecognizing()) {
            recognizerGoogle = new SpeechRecognizerGoogle();
            recognizerGoogle.addListener(new VoiceCommandListener() {
                @Override
                public void onVoiceCommand(String command) {
                    handleCommand(command);
                }

                @Override
                public void onStop() {
                    System.out.println("Speech recognize stopped");
                }
            });
            recognizerGoogle.start();
        } else {
            System.out.println("Already recognizing");
        }
    }

    private void handleCommand(String command) {
        if (command.equals("") || command.equals(" ")) {
            return;
        }
        System.out.println("Got command: " + command);

        VoiceParser.SpeechCommand cmd = voiceParser.parseText(command);
        System.out.println("parsed command: " + cmd.toString());
        switch (cmd) {
            case LIGHTS_ON_ALL:
                hue.changeLightDestination("BOTH");
                hue.toggleAllLights(true);
                break;
            case LIGHTS_OFF_ALL:
                hue.changeLightDestination("BOTH");
                hue.toggleAllLights(false);
                break;
            case LIGHTS_ON_HALLWAY:
                hue.changeLightDestination("HALLWAY");
                hue.toggleAllLights(true);
                break;
            case LIGHTS_OFF_HALLWAY:
                hue.changeLightDestination("HALLWAY");
                hue.toggleAllLights(false);
                break;
            case LIGHTS_ON_BEDROOM:
                hue.changeLightDestination("BED");
                hue.toggleAllLights(true);
                break;
            case LIGHTS_OFF_BEDROOM:
                hue.changeLightDestination("BED");
                hue.toggleAllLights(false);
                break;
            case LIGHTS_ON:
                hue.toggleAllLights(true);
                break;
            case LIGHTS_OFF:
                hue.toggleAllLights(false);
                hue.stopPulsing();
                break;
            case LIGHTS_CHANGE_ALL:
                hue.changeLightDestination("BOTH");
                break;
            case LIGHTS_CHANGE_BEDROOM:
                hue.changeLightDestination("BED");
                break;
            case LIGHTS_CHANGE_HALLWAY:
                hue.changeLightDestination("HALLWAY");
                break;
            case SHOW_NEWS:
                Platform.runLater(() -> newsContainer.setVisible(true));
                break;
            case HIDE_NEWS:
                Platform.runLater(() -> newsContainer.setVisible(false));
                break;
            case SHOW_FORECASTS:
                Platform.runLater(() -> forecastContainer.setVisible(true));
                break;
            case HIDE_FORECASTS:
                Platform.runLater(() -> forecastContainer.setVisible(false));
                break;
            case CHANGE_NEWS_SOURCE:
                news.changeNewsSource();
                new Thread(() -> {
                    String headlines = updateNewsHeadlines();
                    Platform.runLater(() -> {
                        newsHeadlinesLabel.setText(headlines);
                    });

                }).start();
                break;
            case NEXT_BUS:
                try {
                    textToSpeech.doSpeak("Next bus departures in" +  this.nextDeparture);
                } catch (EngineException e) {
                    e.printStackTrace();
                } catch (AudioException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            case UNKNOWN:
                System.out.println("Command not found: " + command);
                break;
        }
               /*
                } else if (command.equals("UP THE BRIGHTNESS ")) {
                    hue.changeBrightness(true);
                } else if (command.equals("REDUCE BRIGHTNESS ")) {
                    hue.changeBrightness(false);
                } else if (command.equals("time to sleep")) {
                    hue.setBrightness(30);
                } else if (command.equals("CHANGE LIGHT ")) {
                    command = hue.changeLightDestination();
                } else if (command.equals("PULSING LIGHT ") || command.equals("DISCO START ")) {
                    hue.pulseLights();
                } else if (command.equals("STOP PULSING ")) {
                    hue.stopPulsing();
                } else if (command.equals("MAXIMUM LIGHT LEVEL ")) {
                    hue.setBrightness(254);
                } else if (command.equals("HELP ME PLEASE ")) {
                    command = "PUT LIGHTS ON\n" +
                            "TURN LIGHTS OFF\n" +
                            "UP THE BRIGHTNESS\n" +
                            "REDUCE BRIGHTNESS\n" +
                            "TIME TO SLEEP\n" +
                            "CHANGE LIGHT\n" +
                            "MAXIMUM LIGHT LEVEL\n" +
                            "HELP ME";
                }
                */

        final String destination = command;

        Platform.runLater(() -> {
            greetingsLabel.setText(destination);
        });
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
                            startRecognize();
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
            Forecasts f = smhiWeatherAPI.getForecasts();
            HourlyForecast weatherNow = f.getCurrentWeather();

            String headlines = updateNewsHeadlines();
            Platform.runLater(() -> {
                newsHeadlinesLabel.setText(headlines);
                Image image = new Image(getClass().getResourceAsStream("/weather-icons/" + WeatherUtils.fileFromInt(weatherNow.getWeatherSymbol())));
                weatherIconView.setImage(image);
                String feelsLike = WeatherUtils.getHeatIndex(weatherNow.getTemp(), weatherNow.getRelativeHumidity());
                currentWeatherLabel.setText(weatherNow.getTemp() + "\u2103" + "\n" + "feels like " + feelsLike + "\u2103" + "\nWind speed " + weatherNow.getWindVelocity() + "m/s");
                //windDirectionLabel.setRotate(weatherNow.getWindDirection());
                image = new Image(getClass().getResourceAsStream("/weather-icons/" + "wind_arrow.png"));
                windDirectionImage.setImage(image);
                windDirectionImage.setRotate(weatherNow.getWindDirection());
            });

            try {
                final Channel channel = yahooWeatherService.getForecast("898091", DegreeUnit.CELSIUS);
                final List<Forecast> forecasts = channel.getItem().getForecasts();
                Platform.runLater(() -> {

                    Forecast tf;
                    for (int i = 0; i < forecasts.size() && i < 5; i++) {
                        tf = forecasts.get(i);
                        dayLabels.get(i).setText(tf.getDay().toString());
                        lowLabels.get(i).setText(tf.getLow() + "°C");
                        highLabels.get(i).setText(tf.getHigh() + "°C");
                        textWeatherLabels.get(i).setText(tf.getText());
                    }
                    Astronomy as = channel.getAstronomy();
                    Time sunRise = as.getSunrise();
                    Time sunset = as.getSunset();
                    System.out.println("Sunrise: " + sunRise.toString() + " Sunset: " + sunset.toString());
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
    private String updateNewsHeadlines() {
        List<String> headlines = news.getLatestHeadlines();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < headlines.size() && i < 6; i++) {
            System.out.println("Headline: " + headlines.get(i));
            sb.append(headlines.get(i).split(" - ")[0] + "\n");
        }
        return sb.toString();
    }

    /**
     * Sets up
     *
     * @param updateInterval in seconds
     */
    private void setupDepartureUpdatesAndTemperature(int updateInterval) {
        System.out.println("Setup departures");
        Runnable updateDepartures = () -> {
            System.out.println("-----------------Updating departures-------------------------");
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
            this.nextDeparture = TimeAndDateConverter.timeToDeparture(js.get(0).getDepDateTime()) + "minutes to Lund LTH";

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

            Platform.runLater(() -> {
                nextJourneyLabel.setText("Upcoming departures to " + js.get(0).getEndStation().getStationName() + " in:\n");
                timeToDepLabel.setText(sb.toString());
                dateDayLabel.setText(getDatePrint());

            });

            /*TemperatureSensorReader temperatureSensorReader = new TemperatureSensorReader();
            double insideTemp = temperatureSensorReader.readTemperatureFirstSensor();
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
            String logMsg = timeStamp + " : " + insideTemp + "°C";
            System.out.println(logMsg);
            TemperatureLogger.appendLog(logMsg + "\n");
            System.out.println("------------TEMPERATURE IS: " + logMsg);*/
            Platform.runLater(() -> {
                nextJourneyLabel.setText("Upcoming departures to " + js.get(0).getEndStation().getStationName() + " in:\n");
                timeToDepLabel.setText(sb.toString());
                dateDayLabel.setText(getDatePrint());
                //insideTemperatureLabel.setText("Inside " + insideTemp + "°C");

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




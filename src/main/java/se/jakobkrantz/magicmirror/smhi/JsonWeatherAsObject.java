package se.jakobkrantz.magicmirror.smhi;
/*
 * Created by krantz on 16-04-19.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"geometry"})

public class JsonWeatherAsObject {
    private ArrayList<TimeSeries> timeSeries;
    private String approvedTime;
    private String referenceTime;

    public ArrayList<TimeSeries> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(ArrayList<TimeSeries> timeSeries) {
        this.timeSeries = timeSeries;
    }

    public String getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(String referenceTime) {
        this.referenceTime = referenceTime;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    @Override
    public String toString() {
        String s = approvedTime + "\n" +
                referenceTime + "\n" +
                timeSeries.size() + "\n" +
                timeSeries.get(0).toString();
        return s;

    }

    public static class TimeSeries {
        private String validTime;
        private ArrayList<Parameter> parameters;


        public ArrayList<Parameter> getParameters() {
            return parameters;
        }

        public void setParameters(ArrayList<Parameter> parameters) {
            this.parameters = parameters;
        }


        public TimeSeries() {

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Parameter p : parameters) {
                sb.append(p.getName() + "\n" +
                        p.getLevel() + "\n" +
                        p.getUnit() + "\n" +
                        p.getLevelType() + "\n" +
                        p.getValues() + "\n" +
                        "--------------\n");
            }
            return sb.toString();
        }

        public String getValidTime() {
            return validTime;
        }

        public void setValidTime(String validTime) {
            this.validTime = validTime;
        }

    }

    static class Parameter {
        private String name;
        private String levelType;
        private String level;
        private String unit;
        private ArrayList<Double> values;


        public ArrayList<Double> getValues() {
            return values;
        }

        public void setValues(ArrayList<Double> values) {
            this.values = values;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevelType() {
            return levelType;
        }

        public void setLevelType(String levelType) {
            this.levelType = levelType;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }


    }
}

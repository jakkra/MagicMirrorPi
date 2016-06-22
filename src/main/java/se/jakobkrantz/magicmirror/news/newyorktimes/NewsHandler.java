package se.jakobkrantz.magicmirror.news.newyorktimes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsHandler {
    private ArrayList<Article> results;

    public ArrayList<Article> getResults() {
        return results;
    }

    public void setResults(ArrayList<Article> results) {
        this.results = results;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Article r: results) {
            sb.append(r.toString() + "\n");
        }
        return sb.toString();

    }

}




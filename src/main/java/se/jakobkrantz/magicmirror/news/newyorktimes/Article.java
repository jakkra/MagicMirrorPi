package se.jakobkrantz.magicmirror.news.newyorktimes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by philip on 2016-05-11.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    public String title;

    @JsonProperty(value = "abstract")
    public String shortDescription;
    public String byline;
    public String published_date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTitle() + "\n" + getShortDescription() + "\n");
        return sb.toString();
    }
}
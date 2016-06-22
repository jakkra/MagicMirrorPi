package se.jakobkrantz.magicmirror.news;

import se.jakobkrantz.magicmirror.news.newyorktimes.NYTNewsAPI;
import se.jakobkrantz.magicmirror.news.yahoo.YahooNews;

import java.util.List;

/**
 * Created by jakkra on 2016-05-28.
 */

public class NewsWrapper {
    private int selected;
    private List<String> latestHeadlines;
    private News[] newsSources;

    public NewsWrapper() {
        newsSources = new News[2];
        newsSources[0] = new YahooNews();
        newsSources[1] = new NYTNewsAPI("technology", "568c91f5cbf0e0d9f500275f7d869ac7:1:75121196");
        selected = 0;
    }

    public List<String> getLatestHeadlines() {
        return newsSources[selected].getLatestHeadlines();
    }


    public void changeNewsSource() {
        selected += 1;
        selected %= 2;

    }

}

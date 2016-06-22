package se.jakobkrantz.magicmirror.news.newyorktimes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NYTNewsAPI implements se.jakobkrantz.magicmirror.news.News {
    private NewsHandler nAPI;
    private String sec;
    String APIkey;

    public NYTNewsAPI(String sec, String APIkey) {
        this.sec = sec;
        this.APIkey = APIkey;
    }

    private NewsHandler getNews() {
        ObjectMapper mapper = new ObjectMapper();
        URLConnection connection = null;

        try {
            System.out.println(new URL("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/" + sec + "/7.json?api-key=" + APIkey));
            connection = (new URL("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/" + sec + "/7.json?api-key=" + APIkey)).openConnection();
            InputStream in = connection.getInputStream();

            nAPI = mapper.readValue(in, NewsHandler.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nAPI;
    }

    @Override
    public List<String> getLatestHeadlines() {
        ArrayList<Article> articles = getNews().getResults();
        ArrayList<String> headlines = new ArrayList<>();
        for (Article a : articles) {
            headlines.add(a.getTitle());
        }
        return headlines;
    }
}
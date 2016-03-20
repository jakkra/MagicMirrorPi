package se.jakobkrantz.magic;
/*
 * Created by krantz on 16-01-05.
 */

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class YahooNews {


    public List<String> getLatestHeadlines() {
        List<SyndEntry> entries = getRSSFeeds("http://news.google.se/news?cf=all&hl=sv&pz=1&ned=sv_se&output=rss");
        Collections.sort(entries, new SyndEntryDateComparator());

        List<String> headlines = new ArrayList<>();
        for (SyndEntry e : entries){
            headlines.add(e.getTitle());
        }

        return headlines;
    }

    public List<SyndEntry> getRSSFeeds(String feedUrl) {
        List<SyndEntry> entries = new ArrayList<>();
        try {
            URL url = new URL(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(url));
            List list = feed.getEntries();
            if (list.size() > 0) {
                System.out.println(list.size());
                for (int j = 0; j < list.size(); j++) {
                    entries = feed.getEntries();
                    for (Object entry1 : entries) {
                        SyndEntry entry = (SyndEntry) entry1;
                        entries.add(entry);
                    }
                }
            }

        } catch (Exception e) {
            System.err.print("Exception");
        }
        return entries;

    }
    public class SyndEntryDateComparator implements Comparator<SyndEntry> {
        @Override
        public int compare(SyndEntry o1, SyndEntry o2) {
            return o1.getPublishedDate().compareTo(o2.getPublishedDate());
        }
    }
}
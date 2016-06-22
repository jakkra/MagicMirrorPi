package se.jakobkrantz.magicmirror.news;

import se.jakobkrantz.magicmirror.news.newyorktimes.Article;

import java.util.List;

/**
 * Created by jakkra on 2016-05-28.
 */

public interface News {

    public List<String> getLatestHeadlines();

}

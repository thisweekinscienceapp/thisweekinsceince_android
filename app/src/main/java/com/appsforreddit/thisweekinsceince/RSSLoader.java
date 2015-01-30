package com.appsforreddit.thisweekinsceince;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.appsforreddit.thisweekinsceince.data.rss.RssFeed;
import com.appsforreddit.thisweekinsceince.data.rss.RssItem;
import com.appsforreddit.thisweekinsceince.data.rss.RssReader;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 1/26/2015.
 */
public class RSSLoader extends AsyncTaskLoader<List<RssItem>> {
    private String url;

    public RSSLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<RssItem> loadInBackground() {
        ArrayList<RssItem> rssItems = new ArrayList<RssItem>();
        try {
            URL urlFeed = new URL(url);
            RssFeed feed = RssReader.read(urlFeed);

            rssItems = feed.getRssItems();
            for (RssItem rssItem : rssItems) {
                Log.i("RSS Reader", rssItem.getTitle());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rssItems;
    }
}

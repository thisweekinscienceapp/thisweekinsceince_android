package com.appsforreddit.thisweekinsceince.data;

import android.os.AsyncTask;

import com.appsforreddit.thisweekinsceince.data.repository.ArticleRepository;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Downloads and parses a webpage with very specific elements.  This seems really fragile
 * and I think we should use rss or json or something more structured.
 */
public class DownloadWebPageTask extends AsyncTask<String, Void, List<Article>> {

    private ArticleRepository.ArticleListAvailableListener listener;

    public DownloadWebPageTask(ArticleRepository.ArticleListAvailableListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Article> doInBackground(String... urls) {
        // this is very very fragile, we need a better way of loading articles.
        // what happens if we get the wrong picture for the wrong description?
        List<String> descriptions = new ArrayList<String>();
        List<String> imageUrls = new ArrayList<String>();

        for (String url2 : urls) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url2);
            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {

                    if (s.contains("<a class=\"title\" href=\"")) {
                        Scanner scan = new Scanner(s);

                        scan.useDelimiter("\"");

                        scan.next();
                        scan.next();
                        scan.next();
                        scan.next();
                        scan.next();

                        descriptions.add(scan.next());
                        scan.close();
                    } else if (s.contains("background-image:")) {
                        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(s);
                        while (m.find()) {
                            imageUrls.add(m.group(1));
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Article> articles = new ArrayList<Article>();
        int i = 0;
        for (String description : descriptions) {
            Article article = new Article();
            article.setShortDescription(description);
            String imageUrl = imageUrls.get(i);
            if (null != imageUrl) {
                article.setImageUrl(imageUrl);      // we are assuming these match up!
            }
            articles.add(article);
            i++;
        }
        return articles;
    }

    @Override
    protected void onPostExecute(List<Article> result) {
        listener.onArticleListAvailable(result);
    }
}
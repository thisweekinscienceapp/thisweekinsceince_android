package com.appsforreddit.thisweekinsceince;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RSSFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private ArrayList<String> url = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private View v;
    private int pageNum=1;




    public RSSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_rs, container, false);
        //ImageView mv = (ImageView) v.findViewById(R.id.imageView);
        //Picasso.with(this.getActivity()).load("http://www.futurism.co/wp-content/uploads/2015/01/Science_Jan25th_2015.jpg").into(mv);
        getRss();
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @SuppressLint("NewApi")
    public void init(View v) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        TableLayout ll = (TableLayout) v.findViewById(R.id.table);

        TextView test;
        ImageView img;
        ImageButton button;
        TableRow.LayoutParams imgSize = new TableRow.LayoutParams(250, 250);
        TableRow.LayoutParams upVote = new TableRow.LayoutParams(170, 125);
        upVote.setMargins(0,60,0,0);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < url.size(); i++) {

            TableRow row = new TableRow(this.getActivity());

            row.setLayoutParams(lp);

            button= new ImageButton(this.getActivity());
            button.setScaleType(ImageView.ScaleType.FIT_XY);
            button.setLayoutParams(upVote);
            button.setImageResource(R.drawable.upvote);

            img = new ImageView(this.getActivity());
            img.setLayoutParams(imgSize);
            Picasso.with(this.getActivity()).load(url.get(i)).into(img);

            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                img.setBackgroundDrawable(null);
                button.setBackgroundDrawable(null);
            } else {
                img.setBackground(null);
                button.setBackground(null);
            }

            test = new TextView(this.getActivity());
            test.setText(title.get(i));

            //row.addView(minusBtn);
            //
            row.addView(button);
            row.addView(img);
            row.addView(test);


            ll.addView(row, i);
        }
    }

    private void getRss()
    {
        if(pageNum<5) {
            pageNum++;
            DownloadWebPageTask task = new DownloadWebPageTask();
            task.execute(new String[]{"http://www.futurism.co/page/" + pageNum});
        }
        else
        {
            init(v);
        }

    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... urls) {
            String response = "";
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

                        if(s.contains("<a class=\"title\" href=\""))
                        {
                            Scanner scan=new Scanner(s);

                            scan.useDelimiter("\"");

                            scan.next();
                            scan.next();
                            scan.next();
                            scan.next();
                            scan.next();

                            title.add(scan.next());
                            scan.close();
                        }
                        else if(s.contains("background-image:")) {
                            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(s);
                            while(m.find()) {
                                url.add(m.group(1));
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            //textView.setText(textView.getText()+result);
            Log.d("resultURL", result);
            getRss();
        }
    }

}

package com.appsforreddit.thisweekinsceince;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.appsforreddit.thisweekinsceince.data.rss.RssFeed;
import com.appsforreddit.thisweekinsceince.data.rss.RssItem;
import com.appsforreddit.thisweekinsceince.data.rss.RssReader;
import com.squareup.picasso.Picasso;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RSSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RSSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RSSFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<RssItem>> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<String> url = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private View v;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RSSFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RSSFragment newInstance(String param1, String param2) {
        RSSFragment fragment = new RSSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RSSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_rs, container, false);
        //ImageView mv = (ImageView) v.findViewById(R.id.imageView);
        //Picasso.with(this.getActivity()).load("http://www.futurism.co/wp-content/uploads/2015/01/Science_Jan25th_2015.jpg").into(mv);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<List<RssItem>> onCreateLoader(int id, Bundle args) {
        return new RSSLoader(getActivity(), "http://www.futurism.co/feed/daily");
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<RssItem>> loader, List<RssItem> data) {
        for (RssItem rssItem : data){
            Log.d("RSS", "rssItem: " + rssItem.getDescription());
            Log.d("RSS Content",""+rssItem.getContent());
            url.add(rssItem.getContent());
            title.add(rssItem.getTitle());
        }
        init(v);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<RssItem>> loader) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    public void init(View v) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        TableLayout ll = (TableLayout) v.findViewById(R.id.table);

        TextView test;
        ImageButton img;
        ImageButton button;
        TableRow.LayoutParams imgSize = new TableRow.LayoutParams(250, 250);
        TableRow.LayoutParams upVote = new TableRow.LayoutParams(170, 125);
        upVote.setMargins(0,60,0,0);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < 5; i++) {

            TableRow row = new TableRow(this.getActivity());

            row.setLayoutParams(lp);

            button= new ImageButton(this.getActivity());
            button.setScaleType(ImageView.ScaleType.FIT_XY);
            button.setLayoutParams(upVote);
            button.setImageResource(R.drawable.upvote);

            img = new ImageButton(this.getActivity());
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

}

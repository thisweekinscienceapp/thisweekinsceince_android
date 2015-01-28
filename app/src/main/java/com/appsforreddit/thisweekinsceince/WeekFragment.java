

package com.appsforreddit.thisweekinsceince;

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import com.squareup.picasso.Picasso;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RSSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RSSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekFragment extends Fragment{


    public WeekFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.temp_layout, container, false);
        ImageView mv = (ImageView) v.findViewById(R.id.imageView);
        Picasso.with(this.getActivity()).load("http://www.futurism.co/wp-content/uploads/2015/01/Science_Jan25th_2015.jpg").into(mv);
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


}

package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.job.activity.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerFragment extends Fragment {


    public PerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.job.fragment
        return inflater.inflate(R.layout.fragment_per, container, false);
    }


}

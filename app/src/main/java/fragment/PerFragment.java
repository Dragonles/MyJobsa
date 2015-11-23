package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.job.activity.AboutWeActivity;
import com.job.activity.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerFragment extends Fragment {


    public TextView tv_About;
    public PerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.job.fragment
        View view = inflater.inflate(R.layout.fragment_per,container,false);

        tv_About = (TextView) view.findViewById(R.id.tv_about);

        tv_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AboutWeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}

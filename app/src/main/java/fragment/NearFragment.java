package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.job.activity.FabuJobDetailsActivity;
import com.job.activity.R;
import com.job.adapter.NearFragmentAdapter;
import com.job.utils.ListViewForScrollView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearFragment extends Fragment {

    ListView listView;
    NearFragmentAdapter nfadapter;
    List<String> list_nf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_near,container,false);
        listView = (ListViewForScrollView) v.findViewById(R.id.lv);

        nfadapter = new NearFragmentAdapter(getActivity().getApplicationContext(),list_nf);
        listView.setAdapter(nfadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), FabuJobDetailsActivity.class);
                startActivity(intent);
            }
        });

        return v;
        // Inflate the layout for this com.job.fragment
//        return inflater.inflate(R.layout.fragment_near, container, false);
    }


}

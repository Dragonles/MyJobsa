package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.job.activity.R;
import com.job.adapter.FabuFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * 2015-1-18
 * yanhao
 */
public class FabuFragment extends Fragment {


    TextView myfabu;    //我的发布
    GridView gridView;  //GridView
    FabuFragmentAdapter fabuFragmentAdapter;    //adapter
    List<Integer> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_fabu, container, false);
        myfabu = (TextView) v.findViewById(R.id.fragment_fabu_myfabu);
        gridView = (GridView) v.findViewById(R.id.fragment_fa_gridview);

        list.add(R.drawable.fragment_fabu_anbao);
        list.add(R.drawable.fragment_fabu_cuxiao);
        list.add(R.drawable.fragment_fabu_fuwuyuan);
        list.add(R.drawable.fragment_fabu_jiajiao);
        list.add(R.drawable.fragment_fabu_jiaqigong);
        list.add(R.drawable.fragment_fabu_linshigong);
        list.add(R.drawable.fragment_fabu_paidan);
        list.add(R.drawable.fragment_fabu_procedure);
        list.add(R.drawable.fragment_fabu_xiaoshou);
        fabuFragmentAdapter = new FabuFragmentAdapter(getActivity().getApplicationContext(),list);
        gridView.setAdapter(fabuFragmentAdapter);

        //TextView点击事件
        myfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"点击了我的发布",Toast.LENGTH_LONG).show();
            }
        });
        //GridView点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),"点击了："+position,Toast.LENGTH_SHORT).show();
            }
        });


        return v;
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_fabu, container, false);

    }


}

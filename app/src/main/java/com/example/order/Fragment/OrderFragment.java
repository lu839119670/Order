package com.example.order.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.order.Adapter.PreviewListviewAdapter;
import com.example.order.Bean.Preview;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private ListView listView;
    private Dao dao;
    private PreviewListviewAdapter previewListviewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.orderfragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.listview);
        dao=new Dao(getContext());
        new Thread(runnable).start();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                handler.postDelayed(runnable,1000);
            }
        }
    };
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (dao.isPreviewNull()){
                List<Preview> list = dao.queryPreview();
                previewListviewAdapter=new PreviewListviewAdapter(list,getContext());
                listView.setAdapter(previewListviewAdapter);
                handler.sendEmptyMessage(0);
            }

        }
    };
}

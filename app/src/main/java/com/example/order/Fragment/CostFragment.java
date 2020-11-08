package com.example.order.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.order.Adapter.CostListViewAdapter;
import com.example.order.Bean.Table;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.List;

public class CostFragment extends Fragment implements View.OnClickListener{
    private ListView listView;
    private TextView totalMoney;
    private TextView trueMoney;
    private Button cost;
    private Dao dao;
    private CostListViewAdapter costListViewAdapter;
    private RelativeLayout table1;
    private RelativeLayout table2;
    private RelativeLayout table3;
    private RelativeLayout table4;
    private TextView tag1;
    private TextView tag2;
    private TextView tag3;
    private TextView tag4;
    private static int tabNum=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.costfragment,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        checkTable();
        listView.setVisibility(View.INVISIBLE);

    }

    private void checkTable() {
        if (dao.queryTableByTable(1).size()!=0){
            table1.setBackgroundResource(R.color.orienge);
            tag1.setText("有人");
        }else {
            table1.setBackgroundResource(R.color.white);
            tag1.setText("空闲");
        }
        if (dao.queryTableByTable(2).size()!=0){
            table2.setBackgroundResource(R.color.orienge);
            tag2.setText("有人");
        }
        else {
            table2.setBackgroundResource(R.color.white);
            tag2.setText("空闲");
        }
        if (dao.queryTableByTable(3).size()!=0){
            table3.setBackgroundResource(R.color.orienge);
            tag3.setText("有人");
        }else {
            table3.setBackgroundResource(R.color.white);
            tag3.setText("空闲");
        }
        if (dao.queryTableByTable(4).size()!=0){
            table4.setBackgroundResource(R.color.orienge);
            tag4.setText("有人");
        }else {
            table4.setBackgroundResource(R.color.white);
            tag4.setText("空闲");
        }
    }

    private void init(View view) {
        listView = view.findViewById(R.id.listview);
        totalMoney=view.findViewById(R.id.textView36);
        trueMoney=view.findViewById(R.id.textView38);
        cost=view.findViewById(R.id.button11);
        dao=new Dao(getContext());
        table1=view.findViewById(R.id.table1);
        table2=view.findViewById(R.id.table2);
        table3=view.findViewById(R.id.table3);
        table4=view.findViewById(R.id.table4);
        tag1=view.findViewById(R.id.textView40);
        tag2=view.findViewById(R.id.textView42);
        tag3=view.findViewById(R.id.textView44);
        tag4=view.findViewById(R.id.textView46);
        table1.setOnClickListener(this);
        table2.setOnClickListener(this);
        table3.setOnClickListener(this);
        table4.setOnClickListener(this);
        cost.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.table1:
                setTableClick(1);
                break;
            case R.id.table2:
                setTableClick(2);
                break;
            case R.id.table3:
                setTableClick(3);
                break;
            case R.id.table4:
                setTableClick(4);
                break;
            case R.id.button11:
                if (tabNum==0){
                    Toast.makeText(getContext(),"请先选择桌号！",Toast.LENGTH_SHORT).show();
                }else {
                    dao.deleteTable(tabNum);
                    listView.setVisibility(View.INVISIBLE);
                    checkTable();
                    totalMoney.setText("0");
                    trueMoney.setText("0");
                    tabNum=0;
                }
                break;
        }
    }
    public void setTotalMoney(List<Table> list){
        int num=0;
        double money=0;
        double total=0;
        for (int i=0;i<list.size();i++){
            num=list.get(i).getNumber();
            money=list.get(i).getMoney();
            total+=num*money;
        }
        totalMoney.setText(total+"");
        trueMoney.setText(total+"");
    }
    public void setTableClick(int number){
        if (dao.queryTableByTable(number).size()!=0){
            listView.setVisibility(View.VISIBLE);
            List<Table> list = dao.queryTableByTable(number);
            costListViewAdapter=new CostListViewAdapter(list,getContext());
            listView.setAdapter(costListViewAdapter);
            setTotalMoney(list);
            tabNum=number;
        }
    }
}

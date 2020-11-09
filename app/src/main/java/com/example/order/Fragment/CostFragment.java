package com.example.order.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

public class CostFragment extends Fragment implements View.OnClickListener {
    private ListView listView;
    private TextView totalMoney;
    private TextView trueMoney;
    private Button cost;
    private Dao dao;
    private CostListViewAdapter costListViewAdapter;
    private RelativeLayout[] tables = new RelativeLayout[4];
    private TextView[] tags = new TextView[4];
    private TextView title;
    private static int tabNum = 0;
    private Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.costfragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        checkTable();
        listView.setVisibility(View.INVISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zhekou = adapterView.getItemAtPosition(i).toString();
                switch (zhekou){
                    case "6折":
                        trueMoney.setText(Double.parseDouble(totalMoney.getText().toString())*0.6+"");
                        break;
                    case "7折":
                        trueMoney.setText(Double.parseDouble(totalMoney.getText().toString())*0.7+"");
                        break;
                    case "8折":
                        trueMoney.setText(Double.parseDouble(totalMoney.getText().toString())*0.8+"");
                        break;
                    case "9折":
                        trueMoney.setText(Double.parseDouble(totalMoney.getText().toString())*0.9+"");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void checkTable() {
        for (int i = 0; i < tables.length; i++) {
            boolean b = dao.queryTableByTable(i + 1).size() != 0;
            tables[i].setBackgroundResource(b ? R.color.orienge : R.color.white);
            tags[i].setText(b ? "有人" : "空闲");
        }
    }




    private void init(View view) {
        spinner=view.findViewById(R.id.spinner);
        listView = view.findViewById(R.id.listview);
        totalMoney = view.findViewById(R.id.textView36);
        trueMoney = view.findViewById(R.id.textView38);
        cost = view.findViewById(R.id.button11);
        dao = new Dao(getContext());
        title=view.findViewById(R.id.textView34);
        tables[0] = view.findViewById(R.id.table1);
        tables[1] = view.findViewById(R.id.table2);
        tables[2] = view.findViewById(R.id.table3);
        tables[3] = view.findViewById(R.id.table4);
        tags[0] = view.findViewById(R.id.textView40);
        tags[1] = view.findViewById(R.id.textView42);
        tags[2] = view.findViewById(R.id.textView44);
        tags[3] = view.findViewById(R.id.textView46);

        for (RelativeLayout table : tables) {
            table.setOnClickListener(this);
        }
        cost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.table1:
                setTableClick(1);
                checkTitle(1);
                break;
            case R.id.table2:
                setTableClick(2);
                checkTitle(2);
                break;
            case R.id.table3:
                setTableClick(3);
                checkTitle(3);
                break;
            case R.id.table4:
                setTableClick(4);
                checkTitle(4);
                break;
            case R.id.button11:
                if (tabNum == 0) {
                    Toast.makeText(getContext(), "请先选择桌号！", Toast.LENGTH_SHORT).show();
                } else {
                    dao.deleteTable(tabNum);
                    listView.setVisibility(View.INVISIBLE);
                    checkTable();
                    totalMoney.setText("0.0");
                    trueMoney.setText("0.0");
                    tabNum = 0;
                    title.setText("请先选择桌号");
                }
                break;
        }
    }

    public void setTotalMoney(List<Table> list) {
        int num = 0;
        double money = 0;
        double total = 0;
        for (Table table : list) {
            num = table.getNumber();
            money = table.getMoney();
            total += num * money;
        }
        totalMoney.setText(total + "");
        trueMoney.setText(total + "");
    }

    public void setTableClick(int number) {
        if (dao.queryTableByTable(number).size() != 0) {
            listView.setVisibility(View.VISIBLE);
            List<Table> list = dao.queryTableByTable(number);
            costListViewAdapter = new CostListViewAdapter(list, getContext());
            listView.setAdapter(costListViewAdapter);
            setTotalMoney(list);
            tabNum = number;
        }
    }
    private void checkTitle(int number){
        if (dao.queryTableByTable(number).size()!=0){
            title.setText("桌号 V00"+number+" 订单");
        }
    }
}

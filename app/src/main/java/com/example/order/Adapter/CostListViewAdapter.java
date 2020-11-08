package com.example.order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.order.Bean.Menu;
import com.example.order.Bean.Table;
import com.example.order.R;

import java.util.List;

public class CostListViewAdapter extends BaseAdapter {
    private List<Table>list;
    private Context context;
    public CostListViewAdapter(List<Table>list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list!=null){
            return list.get(i);
        }
        return null;
    }
    public void update(List<Table> list) {
        this.list = list;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder viewHolder;
        if(view==null){
            viewHolder=new viewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_costlistview, null);
            viewHolder.name=view.findViewById(R.id.textView32);
            viewHolder.money = view.findViewById(R.id.textView47);
            viewHolder.number = view.findViewById(R.id.textView48);
            view.setTag(viewHolder);
        }else {
            viewHolder = (CostListViewAdapter.viewHolder) view.getTag();
        }
            viewHolder.name.setText(list.get(i).getName());
            viewHolder.money.setText("￥ "+list.get(i).getMoney()+" ");
            viewHolder.number.setText("x "+list.get(i).getNumber()+"(份)");
        return view;
    }
    class viewHolder{
        TextView name;
        TextView money;
        TextView number;
    }
}

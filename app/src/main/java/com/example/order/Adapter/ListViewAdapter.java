package com.example.order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.order.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public ListViewAdapter(List<String> list,Context context){
        this.context=context;
        this.list=list;

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
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_listview, null);
            viewHolder.category=view.findViewById(R.id.textView);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.category.setText(list.get(i));
        return view;
    }
    class ViewHolder{
        TextView category;
    }
}

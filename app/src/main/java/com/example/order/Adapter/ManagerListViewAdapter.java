package com.example.order.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.order.Bean.Manager;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.List;

public class ManagerListViewAdapter extends BaseAdapter {
    private List<Manager> list;
    private Context context;
    private AlertDialog alertDialog;
    private Dao dao;
    public ManagerListViewAdapter(List<Manager> list, Context context) {
        this.list = list;
        this.context = context;
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

    @Override
    public long getItemId(int i) {

        return i;
    }
    public void update(List<Manager> list){
        this.list=list;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        viewHolder viewHolder;
        if (view==null){
            viewHolder=new viewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_managerlistview, null);
            viewHolder.xuhao=view.findViewById(R.id.textView56);
            viewHolder.user=view.findViewById(R.id.textView57);
            viewHolder.password=view.findViewById(R.id.textView58);
            viewHolder.name=view.findViewById(R.id.textView59);
            viewHolder.sex=view.findViewById(R.id.textView60);
            viewHolder.position=view.findViewById(R.id.textView61);
            viewHolder.phone=view.findViewById(R.id.textView62);
            viewHolder.imageView=view.findViewById(R.id.imageView2);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ManagerListViewAdapter.viewHolder) view.getTag();
        }
        dao=new Dao(context);
        viewHolder.xuhao.setText(i+1+"");
        viewHolder.user.setText(list.get(i).getUser());
        viewHolder.password.setText(list.get(i).getPassword());
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.sex.setText(list.get(i).getSex());
        viewHolder.position.setText(list.get(i).getPosition());
        viewHolder.phone.setText(list.get(i).getPhone());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDeleteDialog();
            }

            private void createDeleteDialog() {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View v = LayoutInflater.from(context).inflate(R.layout.dialog_managedelete, null);
                TextView message= v.findViewById(R.id.textView69);
                Button delete = v.findViewById(R.id.button15);
                Button cancle = v.findViewById(R.id.button16);
                message.setText("是否要删除 "+list.get(i).getName()+" 的信息？");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dao.deleteManager(list.get(i).getUser());
                        list.remove(i);
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                builder.setView(v);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }
    class viewHolder{
        TextView xuhao;
        TextView user;
        TextView password;
        TextView name;
        TextView sex;
        TextView position;
        TextView phone;
        ImageView imageView;
    }
}

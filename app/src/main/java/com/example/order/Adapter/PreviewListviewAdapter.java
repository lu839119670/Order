package com.example.order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.order.Bean.DishEnum;
import com.example.order.Bean.Preview;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.List;

public class PreviewListviewAdapter extends BaseAdapter {
    private List<Preview> list;
    private Context context;
    private Dao dao;
    private OnItemClickListener mOnItemClickListener;

    public PreviewListviewAdapter(List<Preview> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setaOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int i, DishEnum dish);
    }

    public void update(List<Preview> list) {
        this.list = list;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_previewlstview, null);
            viewHolder.name = view.findViewById(R.id.textView13);
            viewHolder.spicy = view.findViewById(R.id.textView14);
            viewHolder.weight = view.findViewById(R.id.textView15);
            viewHolder.minus = view.findViewById(R.id.button4);
            viewHolder.number = view.findViewById(R.id.textView16);
            viewHolder.add = view.findViewById(R.id.button5);
            viewHolder.totalmoney = view.findViewById(R.id.textView17);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        dao = new Dao(context);
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.spicy.setText(list.get(i).getSpicy());
        viewHolder.weight.setText(list.get(i).getWeight());
        viewHolder.number.setText(list.get(i).getNumber() + "");
        viewHolder.totalmoney.setText("￥ " + list.get(i).getMoney() + "");

        // 添加
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.updatePreviewNumber(
                        dao.getPreviewNumber(list.get(i).getName()) + 1,
                        list.get(i).getName());
                viewHolder.number.setText(dao.getPreviewNumber(list.get(i).getName()) + "");
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(i, DishEnum.pauseToDishEnum(dao.getCategory(list.get(i).getName())));
                }
            }
        });

        // 删除
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preview cache = list.get(i);
                if (dao.getPreviewNumber(list.get(i).getName()) > 1) {
                    dao.updatePreviewNumber(
                            dao.getPreviewNumber(list.get(i).getName()) - 1,
                            list.get(i).getName()
                    );

                    viewHolder.number.setText(dao.getPreviewNumber(list.get(i).getName()) + "");
                } else {
                    dao.deletePreviewItem(list.get(i).getName());
                    list.remove(i);
                    notifyDataSetChanged();
                }

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(i, DishEnum.pauseToDishEnum(dao.getCategory(cache.getName())));
                }
            }
        });
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView spicy;
        TextView weight;
        Button minus;
        TextView number;
        Button add;
        TextView totalmoney;
    }
}

package com.example.order.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.order.Bean.Menu;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private List<Menu> list;
    private OnItemClickListener mOnItemClickListener;

    public GridAdapter(List<Menu> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gride, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(holder, position);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void update(List<Menu> list) {
        this.list = list;
    }


    public interface OnItemClickListener {
        void onItemClick(Menu i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView number;
        private TextView money;
        private Button add;
        private Dao dao;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView11);
            number = itemView.findViewById(R.id.textView3);
            money = itemView.findViewById(R.id.textView12);
            img = itemView.findViewById(R.id.image);
            add = itemView.findViewById(R.id.button3);
            dao = new Dao(itemView.getContext());


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dao.checkPreview(list.get(position).getName())) {
                        dao.updatePreviewNumber(
                                dao.getPreviewNumber(list.get(position).getName()) + 1,
                                list.get(position).getName());
                    } else {
                        dao.createPreview(
                                list.get(position).getName(),
                                list.get(position).getMoney(),
                                list.get(position).getNumber(),
                                list.get(position).getWeight(),
                                list.get(position).getSpicy());
                    }

                    if (dao.getPreviewNumber(list.get(position).getName()) > 0) {
                        number.setText(dao.getPreviewNumber(list.get(position).getName()) + "");
                        number.getBackground().setAlpha(255);
                    }

                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(list.get(position));
                    }
                }
            });

        }

        public void setData(ViewHolder holder, final int position) {
            this.position = position;
            img.setImageResource(list.get(position).getImage());
            name.setText(list.get(position).getName());
            String initNum = number.getText().toString();
            if (dao.checkPreview(list.get(position).getName())) {
                number.setText(dao.getPreviewNumber(list.get(position).getName()) + "");
                number.getBackground().setAlpha(255);
            } else {
                number.setText("");
                number.getBackground().setAlpha(0);
            }
            money.setText(list.get(position).getMoney() + " /份");
        }
    }
}

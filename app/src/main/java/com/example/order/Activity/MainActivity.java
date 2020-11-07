package com.example.order.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.order.Adapter.GridAdapter;
import com.example.order.Adapter.ListViewAdapter;
import com.example.order.Adapter.PreviewListviewAdapter;
import com.example.order.Bean.DishEnum;
import com.example.order.Bean.Menu;
import com.example.order.Bean.Preview;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private List<String> category;
    private ListViewAdapter listViewAdapter;
    private LinearLayout fragment;
    private Button table;
    private Button xiadan;
    private RecyclerView recyclerView;
    private Dao dao;
    private GridAdapter gridAdapter;
    private ListView listView1;
    private List<Preview> previews;
    private AlertDialog alertDialog;
    private List<Menu> createmenu;
    public static int norNum;
    public static int nortab = 0;
    private PreviewListviewAdapter previewListviewAdapter;

    /**
     * 枚举数组，用于确定从数据库中取得哪个类别菜品<br/>
     * 顺序可以更改，改了之后左上的listView顺序也会变化
     */
    private final DishEnum[] DISH_ARRAY = {DishEnum.ENTREE, DishEnum.KOREA, DishEnum.JAPAN, DishEnum.DRINK, DishEnum.SWEETS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (dao.isPreviewNull()) {
            listView1.setVisibility(View.GONE);
            fragment.setVisibility(View.VISIBLE);
        } else {
            fragment.setVisibility(View.GONE);
            listView1.setVisibility(View.VISIBLE);
            listView1.setAdapter(previewListviewAdapter);
        }

        // 左上角ListView的监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                createmenu = dao.createmenu(DISH_ARRAY[i]);
                gridAdapter = new GridAdapter(createmenu);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(gridAdapter);
                recyclerView.invalidateItemDecorations();
                recyclerView.invalidate();
            }
        });

        // 左下角ListView监听
        previewListviewAdapter.setaOnItemClickListener(new PreviewListviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                createmenu = dao.createmenu(DISH_ARRAY[i]);
                gridAdapter = new GridAdapter(createmenu);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(gridAdapter);
                Toast.makeText(MainActivity.this, i + "a", Toast.LENGTH_SHORT).show();
                recyclerView.invalidateItemDecorations();
                recyclerView.invalidate();
            }
        });

        // 右边RecycleView监听
        gridAdapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(MainActivity.this, i + "a", Toast.LENGTH_SHORT).show();
                fragment.setVisibility(View.GONE);
                listView1.setVisibility(View.VISIBLE);
                previews = dao.queryPreview();
                previewListviewAdapter = new PreviewListviewAdapter(previews, MainActivity.this);
                listView1.setAdapter(previewListviewAdapter);
            }
        });
    }

    private void init() {
        listView1 = findViewById(R.id.listview1);
        xiadan = findViewById(R.id.button2);
        fragment = findViewById(R.id.fragment);
        table = findViewById(R.id.button);
        table.setOnClickListener(this);
        dao = new Dao(this);
        previews = dao.queryPreview();
        previewListviewAdapter = new PreviewListviewAdapter(previews, this);

        // 左上角ListView
        listView = findViewById(R.id.listview);
        category = new ArrayList<>();
        for (DishEnum dishEnum : DISH_ARRAY) {
            category.add(dishEnum.getName());
        }
        listViewAdapter = new ListViewAdapter(category, this);
        listView.setAdapter(listViewAdapter);

        // 右边菜单RecycleView
        recyclerView = findViewById(R.id.recyclerview);
        createmenu = dao.createmenu(DishEnum.ENTREE);
        gridAdapter = new GridAdapter(createmenu);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_table, null);
                TextView cancle = v.findViewById(R.id.textView28);
                final TextView oneTable = v.findViewById(R.id.textView19);
                TextView twoTable = v.findViewById(R.id.textView21);
                TextView threeTable = v.findViewById(R.id.textView23);
                TextView foutTable = v.findViewById(R.id.textView25);
                Button minus = v.findViewById(R.id.button6);
                Button add = v.findViewById(R.id.button7);
                TextView number = v.findViewById(R.id.textView27);
                Button choose = v.findViewById(R.id.button8);
                final RelativeLayout table1 = v.findViewById(R.id.table1);
                RelativeLayout table2 = v.findViewById(R.id.table2);
                RelativeLayout table3 = v.findViewById(R.id.table3);
                RelativeLayout table4 = v.findViewById(R.id.table4);

                if (dao.chechTable(1)) {
                    table1.setBackgroundResource(R.color.pink);
                    oneTable.setText("有人");
                } else {
                    table1.setBackgroundResource(R.color.table);
                    oneTable.setText("空闲");
                }

                if (dao.chechTable(2)) {
                    table2.setBackgroundResource(R.color.pink);
                    twoTable.setText("有人");
                } else {
                    table2.setBackgroundResource(R.color.table);
                    twoTable.setText("空闲");
                }
                if (dao.chechTable(3)) {
                    table3.setBackgroundResource(R.color.pink);
                    threeTable.setText("有人");
                } else {
                    table3.setBackgroundResource(R.color.table);
                    threeTable.setText("空闲");
                }
                if (dao.chechTable(4)) {
                    table4.setBackgroundResource(R.color.pink);
                    foutTable.setText("有人");
                } else {
                    table4.setBackgroundResource(R.color.table);
                    foutTable.setText("空闲");
                }

                table1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dao.chechTable(1)) {
                            table1.setBackgroundResource(R.color.pink);
                            oneTable.setText("有人");
                        } else {

                            if (nortab==0){
                                oneTable.setText("asd");
                            }
                        }
                    }
                });
                table2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                table3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                table4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });


                builder.setView(v);
                alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.button2:
                break;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dao.deletePreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.deletePreview();
    }
}

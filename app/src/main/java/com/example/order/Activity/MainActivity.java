package com.example.order.Activity;

import android.app.AlertDialog;
import android.content.Intent;
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
    // 左上角
    private ListView listView;
    // 左上角listview的监听器
    private ListViewAdapter listViewAdapter;
    private LinearLayout fragment;
    private Button table;
    private Button xiadan;
    private RecyclerView recyclerView;
    private Dao dao;
    private GridAdapter gridAdapter;
    private ListView listView1;
    private AlertDialog alertDialog;
    public static int norNum = 1;
    public static int nortab = 0;
    private TextView totalNumber;
    private TextView totalMoney;
    private PreviewListviewAdapter previewListviewAdapter;

    /**
     * 枚举数组，用于确定从数据库中取得哪个类别菜品<br/>
     * 顺序可以更改，改了之后左上的listView顺序也会变化
     */
    private static final DishEnum[] DISH_ARRAY = {DishEnum.ENTREE, DishEnum.KOREA, DishEnum.JAPAN, DishEnum.DRINK, DishEnum.SWEETS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        checkListStatus();
        setTotalNumber();
        setTotalMoney();

        // 左上角ListView的监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateMenuView(DISH_ARRAY[i]);
            }
        });

        // 左下角ListView监听
        previewListviewAdapter.setaOnItemClickListener(new PreviewListviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, DishEnum dish) {
                updateMenuView(dish);
                setTotalNumber();
                setTotalMoney();

                // 通过寻找物品在菜单中的位置来进行跳转
                // 新增加代码请写在这段代码的上方，否则可能不被执行
                // 判断是否少了个元素，防止发生OutOfBounds
                List<Preview> previews = dao.queryPreview();
                if (previews.size() <= i) return;
                // 获取菜品名称
                String name = previews.get(i).getName();
                // 获取此菜单内容数量
                List<Menu> menus = dao.createmenu(dish);
                // 遍历对比，对上就进行跳转
                for (int j = 0; j < menus.size(); j++) {
                    if (name.equals(menus.get(j).getName())) {
                        // 跳转，参数是adapter中内容的位置，而不是像素
                        recyclerView.smoothScrollToPosition(j);
                    }
                }
            }
        });

        // 右边RecycleView监听
        gridAdapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Menu menu) {
                // 点击之后刷新左下角的listview
                fragment.setVisibility(View.GONE);
                listView1.setVisibility(View.VISIBLE);
                List<Preview> previews = dao.queryPreview();
                previewListviewAdapter.update(previews);
                setTotalNumber();
                setTotalMoney();

                for (int i = 0; i < previews.size(); i++) {
                    if (menu.getName().equals(previews.get(i).getName())) {
                        listView1.smoothScrollToPosition(i);
                    }
                }
            }
        });
    }

    /**
     * 刷新右边主界面
     *
     * @param witch 需要哪个
     */
    private void updateMenuView(DishEnum witch) {
        List<Menu> menus = dao.createmenu(witch);
        gridAdapter.update(menus);
        checkListStatus();
    }

    /**
     * 根据Preview是否为空来刷新UI
     */
    private void checkListStatus() {
        if (dao.isPreviewNull()) {
            listView1.setVisibility(View.GONE);
            fragment.setVisibility(View.VISIBLE);
        } else {
            fragment.setVisibility(View.GONE);
            listView1.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        totalNumber = findViewById(R.id.textView5);
        totalMoney = findViewById(R.id.textView8);
        xiadan = findViewById(R.id.button2);
        fragment = findViewById(R.id.fragment);
        table = findViewById(R.id.button);
        table.setOnClickListener(this);
        xiadan.setOnClickListener(this);
        dao = new Dao(this);

        // 左下角ListView
        listView1 = findViewById(R.id.listview1);
        List<Preview> previews = dao.queryPreview();
        previewListviewAdapter = new PreviewListviewAdapter(previews, this);
        listView1.setAdapter(previewListviewAdapter);

        // 左上角ListView
        listView = findViewById(R.id.listview);
        List<String> category = new ArrayList<>();
        for (DishEnum dishEnum : DISH_ARRAY) {
            category.add(dishEnum.getName());
        }
        listViewAdapter = new ListViewAdapter(category, this);
        listView.setAdapter(listViewAdapter);

        // 右边菜单RecycleView
        recyclerView = findViewById(R.id.recyclerview);
        List<Menu> createmenu = dao.createmenu(DishEnum.ENTREE);
        gridAdapter = new GridAdapter(createmenu);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (dao.queryPreview().size() == 0) {
                    Toast.makeText(this, "请先选择菜哦！", Toast.LENGTH_SHORT).show();
                } else {
                    tableButtonClicked();
                }
                break;
            case R.id.button2:
                if (nortab > 0) {
                    List<Preview> mtbale = dao.queryPreview();
                    for (Preview preview : mtbale) {
                        dao.createMtable(nortab, norNum, preview.getName(), preview.getMoney(), preview.getNumber(), preview.getWeight(), preview.getSpicy());
                    }
                    managerLogin();
                    dao.deletePreview();
                    gridAdapter.update(dao.createmenu(DishEnum.ENTREE));
                    previewListviewAdapter.update(new ArrayList<Preview>());
                    checkListStatus();
                    nortab=0;
                    totalNumber.setText("0");
                    totalMoney.setText("0");
                } else {
                    Toast.makeText(this, "请先选择哪号桌！", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    private void managerLogin() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_login, null);
        final EditText user = v.findViewById(R.id.editText);
        final EditText password = v.findViewById(R.id.editText2);
        Button login = v.findViewById(R.id.button9);
        Button cancleLogin = v.findViewById(R.id.button10);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isLogin = dao.login(user.getText().toString(), password.getText().toString());
                if (isLogin) {
                    startActivity(new Intent(MainActivity.this, ManageActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "输入的账户或密码有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void tableButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_table, null);
        TextView cancle = v.findViewById(R.id.textView28);
        Button minus = v.findViewById(R.id.button6);
        Button add = v.findViewById(R.id.button7);
        final TextView number = v.findViewById(R.id.textView27);
        Button choose = v.findViewById(R.id.button8);

        final TextView[] tableTextView = new TextView[4];
        tableTextView[0] = v.findViewById(R.id.textView19);
        tableTextView[1] = v.findViewById(R.id.textView21);
        tableTextView[2] = v.findViewById(R.id.textView23);
        tableTextView[3] = v.findViewById(R.id.textView25);

        final RelativeLayout[] tableRelativeLayout = new RelativeLayout[4];
        tableRelativeLayout[0] = v.findViewById(R.id.table1);
        tableRelativeLayout[1] = v.findViewById(R.id.table2);
        tableRelativeLayout[2] = v.findViewById(R.id.table3);
        tableRelativeLayout[3] = v.findViewById(R.id.table4);
        number.setText(norNum + "");

        // 大量重复代码可以通过数组进行复用
        // for循环 区间：[0, 3]
        for (int i = 0; i < tableTextView.length; i++) {
            // 遍历所有桌子是否有人，设置状态
            boolean hasPeople = dao.chechTable(i + 1);
            tableRelativeLayout[i].setBackgroundResource(hasPeople ? R.color.pink : R.color.table);
            tableTextView[i].setText(hasPeople ? "有人" : "空闲");

            final int finalI = i;
            // 循环添加点击监听
            tableRelativeLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 点击的时候再从数据库取一次是否有人
                    boolean t1 = dao.chechTable(finalI + 1);

                    // 如果没有人
                    if (!t1) {
                        // 将layout设置成橙色表示已经选中
                        tableRelativeLayout[finalI].setBackgroundResource(R.color.orienge);
                        // 将全局变量设为当前选中桌子
                        nortab = finalI + 1;
                        // 遍历循环将其他桌子设置颜色
                        for (int j = 0; j < tableRelativeLayout.length; j++) {
                            // 遍历其他桌子是否有人
                            boolean t2 = dao.chechTable(j + 1);
                            // 判断是不是当前选中的桌子
                            if (j != finalI) {
                                // 三元修改颜色
                                tableRelativeLayout[j].setBackgroundResource(t2 ? R.color.pink : R.color.table);
                            }
                        }
                    }
                }
            });
        }

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (norNum > 1) {
                    norNum--;
                    number.setText(norNum + "");
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                norNum++;
                number.setText(norNum + "");
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public void setTotalNumber() {
        List<Preview> que = dao.queryPreview();
        int num = 0;
        if (que != null) {
            for (Preview preview : que) {
                num += preview.getNumber();
            }
            totalNumber.setText(num + "");
        }
    }

    public void setTotalMoney() {
        List<Preview> que = dao.queryPreview();
        int num;
        double money;
        double totalmoneies = 0;
        if (que != null) {
            for (Preview preview : que) {
                num = preview.getNumber();
                money = preview.getMoney();
                totalmoneies += num * money;
            }
            totalMoney.setText("￥ " + totalmoneies + "");
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

package com.example.order.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.order.Adapter.ManagerListViewAdapter;
import com.example.order.Bean.Manager;
import com.example.order.Dao.Dao;
import com.example.order.R;

import java.util.List;

public class ManagerFragment extends Fragment {
    private ListView listView;
    private List<Manager> list;
    private Dao dao;
    private Button add;
    private AlertDialog alertDialog;
    private ManagerListViewAdapter managerListViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.managerfragment,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        listView.setAdapter(managerListViewAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAddDialog();
            }
        });
    }

    private void init(View view) {
        listView= view.findViewById(R.id.managerListview);
        add=view.findViewById(R.id.button12);
        dao=new Dao(getContext());
        list=dao.queryManager();
        managerListViewAdapter=new ManagerListViewAdapter(list,getContext());
    }
    public void createAddDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_addmanager, null);
        final EditText user = v.findViewById(R.id.editText3);
        final EditText password = v.findViewById(R.id.editText4);
        final EditText name = v.findViewById(R.id.editText5);
        final EditText sex = v.findViewById(R.id.editText6);
        final EditText position = v.findViewById(R.id.editText7);
        final EditText pohone = v.findViewById(R.id.editText8);
        Button addManager = v.findViewById(R.id.button13);
        Button cancle = v.findViewById(R.id.button14);

        addManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = user.getText().toString();
                String passwordValue = password.getText().toString();
                String nameValue = name.getText().toString();
                String sexValue = sex.getText().toString();
                String positionValue = position.getText().toString();
                String phoneValue = pohone.getText().toString();
                if (userValue.equals("")){
                    Toast.makeText(getContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if (passwordValue.equals("")){
                    Toast.makeText(getContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if (nameValue.equals("")){
                    Toast.makeText(getContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (sexValue.equals("")){
                    Toast.makeText(getContext(),"性别不能为空",Toast.LENGTH_SHORT).show();
                }else if (positionValue.equals("")){
                    Toast.makeText(getContext(),"职位不能为空",Toast.LENGTH_SHORT).show();
                }else if (phoneValue.equals("")){
                    Toast.makeText(getContext(),"手机号码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    dao.createManager(userValue,passwordValue,nameValue,sexValue,positionValue,phoneValue);
                    alertDialog.dismiss();
                    List<Manager> list1 = dao.queryManager();
                    managerListViewAdapter.update(list1);
                    managerListViewAdapter.notifyDataSetChanged();
                }
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
}

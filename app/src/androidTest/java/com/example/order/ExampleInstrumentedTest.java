package com.example.order;

import android.content.Context;
import android.widget.Adapter;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.order.Dao.Dao;
import com.example.order.Dao.DataBaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
        public void create(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DataBaseHelper dataBaseHelper=new DataBaseHelper(appContext);
        dataBaseHelper.getReadableDatabase();
    }

    @Test
        public void insertMenu(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao=new Dao(appContext);
        dao.insertMenu("主菜","夫妻肺片",35.0,"适中",1,"微辣",R.drawable.fuqifeipian);
        dao.insertMenu("主菜","干锅肥肠",45.0,"适中",1,"微辣",R.drawable.ganguofeichang);
        dao.insertMenu("主菜","宫保鸡丁",30.0,"适中",1,"微辣",R.drawable.gongbaojiding);
        dao.insertMenu("主菜","烤鱼",89.0,"适中",1,"微辣",R.drawable.kaoyu);
        dao.insertMenu("主菜","口水鸡",30.0,"适中",1,"微辣",R.drawable.koushuiji);
        dao.insertMenu("主菜","爆炒牛肉",50.0,"适中",1,"微辣",R.drawable.lachaoniurou);
        dao.insertMenu("主菜","凉拌魔芋",20.0,"适中",1,"微辣",R.drawable.liangbanmoyu);
        dao.insertMenu("主菜","麻辣牛肉",25.0,"适中",1,"微辣",R.drawable.malaniurou);
        dao.insertMenu("主菜","麻婆豆腐",10.0,"适中",1,"微辣",R.drawable.mapodoufu);
        dao.insertMenu("主菜","牛排",55.0,"适中",1,"微辣",R.drawable.niupai);
        dao.insertMenu("主菜","鱼香肉丝",33.0,"适中",1,"微辣",R.drawable.yuxiangrousi);

        dao.insertMenu("韩国料理","烤牛骨",55.0,"适中",1,"微辣",R.drawable.kaoniugu);
        dao.insertMenu("韩国料理","辣炒腐竹",35.0,"适中",1,"微辣",R.drawable.lachaofuzhu);
        dao.insertMenu("韩国料理","泡菜",15.0,"适中",1,"微辣",R.drawable.paocai);
        dao.insertMenu("韩国料理","石锅拌饭",32.0,"适中",1,"微辣",R.drawable.shiguobanfan);

        dao.insertMenu("日本料理","关东煮",25.0,"适中",1,"微辣",R.drawable.guandongzhu);
        dao.insertMenu("日本料理","三文鱼",58.0,"适中",1,"无",R.drawable.sanwenyu);
        dao.insertMenu("日本料理","生鱼片套餐",88.0,"适中",1,"无",R.drawable.shengyupian);
        dao.insertMenu("日本料理","寿司",78.0,"适中",1,"无",R.drawable.shousi);

        dao.insertMenu("饮料","草莓奶昔",15.0,"适中",1,"无",R.drawable.caomeinaixi);
        dao.insertMenu("饮料","橙汁",10.0,"适中",1,"无",R.drawable.chengzhi);
        dao.insertMenu("饮料","红酒",88.0,"适中",1,"无",R.drawable.hongjiu);
        dao.insertMenu("饮料","柠檬水",10.0,"适中",1,"无",R.drawable.ningmengshui);
        dao.insertMenu("饮料","巧克力奶昔",20.0,"适中",1,"无",R.drawable.qiaokelinaixi);
        dao.insertMenu("饮料","雪碧",10.0,"适中",1,"无",R.drawable.xuebi);

        dao.insertMenu("甜品","巧乐力蛋糕",22.0,"适中",1,"无",R.drawable.qiaokelidangao);
        dao.insertMenu("甜品","红枣蛋糕",19.0,"适中",1,"无",R.drawable.hongzaodangao);
    }

    @Test
    public void change(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao=new Dao(appContext);
        dao.change(R.drawable.fuqifeipian,"夫妻肺片");
        dao.change(R.drawable.ganguofeichang,"干锅肥肠");
        dao.change(R.drawable.gongbaojiding,"宫保鸡丁");
        dao.change(R.drawable.kaoyu,"烤鱼");
        dao.change(R.drawable.koushuiji,"口水鸡");
        dao.change(R.drawable.lachaoniurou,"爆炒牛肉");
        dao.change(R.drawable.liangbanmoyu,"凉拌魔芋");
        dao.change(R.drawable.malaniurou,"麻辣牛肉");
        dao.change(R.drawable.mapodoufu,"麻婆豆腐");
        dao.change(R.drawable.niupai,"牛排");
        dao.change(R.drawable.yuxiangrousi,"鱼香肉丝");

        dao.change(R.drawable.kaoniugu,"烤牛骨");
        dao.change(R.drawable.lachaofuzhu,"辣炒腐竹");
        dao.change(R.drawable.paocai,"泡菜");
        dao.change(R.drawable.shiguobanfan,"石锅拌饭");

        dao.change(R.drawable.guandongzhu,"关东煮");
        dao.change(R.drawable.sanwenyu,"三文鱼");
        dao.change(R.drawable.shengyupian,"生鱼片套餐");
        dao.change(R.drawable.shousi,"寿司");

        dao.change(R.drawable.caomeinaixi,"草莓奶昔");
        dao.change(R.drawable.chengzhi,"橙汁");
        dao.change(R.drawable.hongjiu,"红酒");
        dao.change(R.drawable.ningmengshui,"柠檬水");
        dao.change(R.drawable.qiaokelinaixi,"巧克力奶昔");
        dao.change(R.drawable.xuebi,"雪碧");

        dao.change(R.drawable.qiaokelidangao,"巧乐力蛋糕");
        dao.change(R.drawable.hongzaodangao,"红枣蛋糕");

    }
    @Test
    public void delete(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Dao dao=new Dao(appContext);
        dao.deletePreview();

    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.order", appContext.getPackageName());
    }
}

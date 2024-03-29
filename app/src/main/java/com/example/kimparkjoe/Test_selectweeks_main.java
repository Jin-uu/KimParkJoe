package com.example.kimparkjoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Test_selectweeks_main extends AppCompatActivity implements View.OnClickListener {

    private View view;

    public static int curr_week;
    public static Activity test_select_weeks_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selectweeks_main);
        Log.d("final_log","테스트 주차 선택 전환");

        test_select_weeks_activity = Test_selectweeks_main.this;

        findViewById(R.id.tv_test_week_2).setOnClickListener(this);
        findViewById(R.id.tv_test_week_3).setOnClickListener(this);
        findViewById(R.id.tv_test_week_4).setOnClickListener(this);
        findViewById(R.id.tv_test_week_1).setOnClickListener(this);
        findViewById(R.id.tv_test_week_5).setOnClickListener(this);
        findViewById(R.id.tv_test_week_6).setOnClickListener(this);
        findViewById(R.id.tv_test_week_7).setOnClickListener(this);
        findViewById(R.id.tv_test_week_8).setOnClickListener(this);
        findViewById(R.id.tv_test_week_9).setOnClickListener(this);
        findViewById(R.id.tv_test_week_10).setOnClickListener(this);
        findViewById(R.id.tv_test_week_11).setOnClickListener(this);
        findViewById(R.id.tv_test_week_12).setOnClickListener(this);
        findViewById(R.id.tv_test_week_13).setOnClickListener(this);
        findViewById(R.id.tv_test_week_14).setOnClickListener(this);
        findViewById(R.id.tv_test_week_15).setOnClickListener(this);
        findViewById(R.id.tv_test_week_16).setOnClickListener(this);

        findViewById(R.id.btn_test_week_quit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_test_week_quit:
                Log.d("final_log","테스트 주차선택 back 버튼 눌림");
                finish();
                break;
            default:
                String weekString = (String) ((TextView)view).getText();
                String[] week_array = weekString.split(" ");
                curr_week = Integer.parseInt(week_array[1]);
                System.out.println("curr_week : "+curr_week);
                ((MainActivity)MainActivity.context_main).putWordsFromDB(curr_week);
//                MainActivity main = (MainActivity) MainActivity.activity_main;
//                main.putWordsFromDB(curr_week);
                System.out.println("wordMap -> "+MainActivity.wordMap);
                myStartActivity(Test_testing_main.class);
        }
    }

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
package cn.edu.swufe.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class basketball extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketball);

        Button j3 = findViewById(R.id.j3);
        Button j2 = findViewById(R.id.j2);
        Button j1 = findViewById(R.id.j1);
        Button j3b = findViewById(R.id.j3b);
        Button j2b = findViewById(R.id.j2b);
        Button j1b = findViewById(R.id.j1b);
        Button clear = findViewById(R.id.clear);
        Button btos = findViewById(R.id.btos);//获取控件

        MyListener listener = new MyListener();
        j3.setTag(1);
        j3.setOnClickListener(listener);
        j2.setTag(2);
        j2.setOnClickListener(listener);
        j1.setTag(3);
        j1.setOnClickListener(listener);

        j3b.setTag(11);
        j3b.setOnClickListener(listener);
        j2b.setTag(12);
        j2b.setOnClickListener(listener);
        j1b.setTag(12);
        j1b.setOnClickListener(listener);

        clear.setTag(4);                    //给篮球计数页面BUTTON设置标记
        clear.setOnClickListener(listener);

        btos.setTag(5);
        btos.setOnClickListener(listener);//给页面转换按钮设置标记
    }


    public class MyListener implements View.OnClickListener {
        TextView tsc = (TextView) findViewById(R.id.tsc);
        TextView tscb = (TextView) findViewById(R.id.tscb);
        int score = 0;
        int scoreb = 0;

        public void onClick(View v) {
            int tag =(Integer)v.getTag();

            switch (tag) {
                case 1:
                    score = score + 3;
                    tsc.setText(String.valueOf(score));
                    //Log.i("main", "click finished");
                    break;
                case 2:
                    score = score + 2;
                    tsc.setText(String.valueOf(score));
                    break;
                case 3:
                    score = score + 1;
                    tsc.setText(String.valueOf(score));
                    break;

                case 11:
                    scoreb = scoreb + 3;
                    tscb.setText(String.valueOf(scoreb));
                    //Log.i("main", "click finished");
                    break;
                case 12:
                    scoreb = scoreb + 2;
                    tscb.setText(String.valueOf(scoreb));
                    break;
                case 13:
                    scoreb = scoreb + 1;
                    tscb.setText(String.valueOf(scoreb));
                    break;


                case 4:
                    score = 0;
                    scoreb=0;
                    tsc.setText(String.valueOf(score));
                    tscb.setText(String.valueOf(scoreb));
                    break;
                case 5:
                    //setContentView(R.layout.second);
                    Intent intent = new Intent(basketball.this, MainActivity.class);// 切换页面到温度转换
                    startActivity(intent);
                    break;
            }
        }

    }
}



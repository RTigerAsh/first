package cn.edu.swufe.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView out;
    EditText input;
    String bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        //setContentView(R.layout.basketball);

        out = findViewById(R.id.text3);
        input = findViewById(R.id.inp1);


        //but.setOnClickListener(this);
        Button but=findViewById(R.id.but1);

        Button j3=findViewById(R.id.j3);
        Button j2=findViewById(R.id.j2);
        Button j1=findViewById(R.id.j1);
        Button clear=findViewById(R.id.clear);
        Button stob=findViewById(R.id.stob);
        Button btos=findViewById(R.id.btos);//获取控件

        MyListener listener=new MyListener();
        j3.setTag(1);
        j3.setOnClickListener(listener);
        j2.setTag(2);
        j2.setOnClickListener(listener);
        j1.setTag(3);
        j1.setOnClickListener(listener);
        clear.setTag(4);                    //给篮球计数页面BUTTON设置标记
        clear.setOnClickListener(listener);

        stob.setTag(5);
        stob.setOnClickListener(listener);
        btos.setTag(6);
        btos.setOnClickListener(listener);//给页面转换按钮设置标记

        but.setTag(7);
        but.setOnClickListener(listener);//给温度转换按钮设置标记


    }

    /*public void onClick(View v) {

        double inp1=Double.parseDouble(input.getText().toString());
        double fs=1.8*inp1+32;

        bs=String.format("%.2f",fs);//转换为两位小数

        out.setText("结果为："+bs);
        Log.i("main", "click finished");

    }*/

    public class MyListener implements View.OnClickListener{
        TextView tsc=(TextView)findViewById(R.id.tsc);
        int score=0;

        public void onClick(View v){
            int tag =(Integer)v.getTag();

            switch (tag){
                case 1:
                    score=score+3;
                    tsc.setText(String.valueOf(score));
                    //Log.i("main", "click finished");
                    break;
                case 2:
                    score=score+2;
                    tsc.setText(String.valueOf(score));
                    break;
                case 3:
                    score=score+1;
                    tsc.setText(String.valueOf(score));
                    break;
                case 4:
                    score=0;
                    tsc.setText(String.valueOf(score));
                    break;
                case 5:
                    setContentView(R.layout.basketball);//切换页面到篮球计数
                    break;
                case 6:
                    setContentView(R.layout.second);//切换页面到温度转换
                    break;
                case 7:
                    double inp1=Double.parseDouble(input.getText().toString());
                    double fs=1.8*inp1+32;

                    bs=String.format("%.2f",fs);//转换为两位小数

                    out.setText("结果为："+bs);
                    Log.i("main", "click finished");
                    break;
            }
        }


    }
}

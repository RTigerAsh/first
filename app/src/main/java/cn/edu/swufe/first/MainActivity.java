package cn.edu.swufe.first;

import android.content.Intent;
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

        out = findViewById(R.id.text3);
        input = findViewById(R.id.inp1);


        Button but=findViewById(R.id.but1);
        Button stob=findViewById(R.id.stob);//获取控件

        MyListener listener=new MyListener();
        but.setTag(1);
        but.setOnClickListener(listener);//给温度转换按钮设置标记

        stob.setTag(2);
        stob.setOnClickListener(listener);//给页面转换按钮设置标记




    }

    public class MyListener implements View.OnClickListener{

        public void onClick(View v){
            int tag =(Integer)v.getTag();

            switch (tag){

               case 1:
                    double inp1=Double.parseDouble(input.getText().toString());
                    double fs=1.8*inp1+32;

                    bs=String.format("%.2f",fs);//转换为两位小数

                    out.setText("结果为："+bs);
                    Log.i("main", "click finished");
                    break;
                case 2:
                    Intent intent =new Intent(MainActivity.this,basketball.class);
                    startActivity(intent);//切换页面到篮球计数
                    break;

            }
        }


    }
}

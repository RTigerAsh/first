package cn.edu.swufe.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

        EditText rmb,edit_dollar,edit_euro,edit_con;
        TextView showrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

        rmb=(EditText) findViewById(R.id.rmb);
        edit_dollar=(EditText)findViewById(R.id.edit_dollar);
        edit_euro=(EditText)findViewById(R.id.edit_euro);
        edit_con=(EditText)findViewById(R.id.edit_con);
        showrate=(TextView)findViewById(R.id.showrate);

        Button dollar = findViewById(R.id.dollar);
        Button euro = findViewById(R.id.euro);
        Button con = findViewById(R.id.con);

        MyListener listener = new MyListener();
        dollar.setTag(1);
        dollar.setOnClickListener(listener);
        euro.setTag(2);
        euro.setOnClickListener(listener);
        con.setTag(3);
        con.setOnClickListener(listener);
        edit_dollar.setTag(4);
        edit_dollar.setOnClickListener(listener);
        edit_euro.setTag(5);
        edit_euro.setOnClickListener(listener);
        edit_con.setTag(6);
        edit_con.setOnClickListener(listener);

    }

    public class MyListener implements View.OnClickListener {

        public void onClick(View v) {

            int tag =(Integer)v.getTag();

            String str=rmb.getText().toString();
            String strdo=edit_dollar.getText().toString();
            String streu=edit_euro.getText().toString();
            String strco=edit_con.getText().toString();

            double dorate=1/6.7;
            double eurate=1/11.0;
            double corate=500;

            switch (tag){

                case 1:

                    if (str.length()>0){
                        double r=Double.parseDouble(str);
                        r=r*(1/dorate);
                        String bs=String.format("%.2f",r);
                        showrate.setText(bs);
                    }else {
                        //Log.i("main", "click finished"+str);
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 2:

                    if (str.length()>0){
                        double r=Double.parseDouble(str);
                        r=r*(1/eurate);
                        String bs=String.format("%.2f",r);
                        showrate.setText(bs);
                    }else {
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:

                    if (str.length()>0){
                        double r=Double.parseDouble(str);
                        r=r*corate;
                        String bs=String.format("%.2f",r);
                        showrate.setText(bs);
                    }else {
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 4:
                    if (strdo.length()>0){
                        Double edit_dollar= Double.parseDouble(strdo);
                        dorate=edit_dollar;
                        Toast.makeText(RateActivity.this,"汇率已修改",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RateActivity.this,"汇率未修改",Toast.LENGTH_SHORT).show();
                    }
                case 5:
                    if (streu.length()>0){
                        Double edit_dollar= Double.parseDouble(strdo);
                        dorate=edit_dollar;
                        Toast.makeText(RateActivity.this,"汇率已修改",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RateActivity.this,"汇率未修改",Toast.LENGTH_SHORT).show();
                    }
                case 6:
                    if (strco.length()>0){
                        Double edit_dollar= Double.parseDouble(strdo);
                        dorate=edit_dollar;
                        Toast.makeText(RateActivity.this,"汇率已修改",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RateActivity.this,"汇率未修改",Toast.LENGTH_SHORT).show();
                    }

            }
        }
    }
}

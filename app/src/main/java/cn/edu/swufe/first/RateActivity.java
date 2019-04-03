package cn.edu.swufe.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

        EditText rmb;
        TextView showrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

        rmb=(EditText) findViewById(R.id.rmb);
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

    }

    public class MyListener implements View.OnClickListener {

        String str=rmb.getText().toString();

        double r=Double.parseDouble(str);

        public void onClick(View v) {
            int tag =(Integer)v.getTag();
            switch (tag){
                case 1:
                    if (str.length()>0){
                        double var=r;
                        var=r*(1/6.7);
                        String bs=String.format("%.2f",var);
                        showrate.setText(bs);
                    }else {
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if (str.length()>0){
                        double var=r;
                        var=r*(1/11d);
                        String bs=String.format("%.2f",var);
                        showrate.setText(bs);
                    }else {
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:
                    if (str.length()>0){
                        double var=r;
                        var=r*500;
                        String bs=String.format("%.2f",var);
                        showrate.setText(bs);
                    }else {
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }



        }


    }
}

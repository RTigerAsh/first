package cn.edu.swufe.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

        EditText rmb;
        TextView showrate;
        Double dorate,eurate,corate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

        /*Intent intent=getIntent();
        dorate=intent.getDoubleExtra("dorate",7.0);
        eurate=intent.getDoubleExtra("eurate",11.0);
        corate=intent.getDoubleExtra("corate",500.0);*/

        Bundle bundle=this.getIntent().getExtras();
        dorate=bundle.getDouble("dorate");
        eurate=bundle.getDouble("eurate");
        corate=bundle.getDouble("corate");

        Log.i("RateActivity", "dollar rate change finished--->"+dorate);
        Log.i("RateActivity", "euro rate change finished--->"+eurate);
        Log.i("RateActivity", "con rate change finished--->"+corate);

        rmb=(EditText) findViewById(R.id.rmb);
        showrate=(TextView)findViewById(R.id.showrate);

        Button dollar = findViewById(R.id.dollar);
        Button euro = findViewById(R.id.euro);
        Button con = findViewById(R.id.con);
        Button changerate=findViewById(R.id.button_changerate);

        MyListener listener = new MyListener();
        dollar.setTag(1);
        dollar.setOnClickListener(listener);
        euro.setTag(2);
        euro.setOnClickListener(listener);
        con.setTag(3);
        con.setOnClickListener(listener);
        changerate.setTag(4);
        changerate.setOnClickListener(listener);

    }

    public class MyListener implements View.OnClickListener {

        public void onClick(View v) {

            int tag =(Integer)v.getTag();

            String str=rmb.getText().toString();

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
                    Intent intent =new Intent(RateActivity.this,ChangeRateActivity.class);
                    startActivity(intent);//切换页面到修改汇率
                    break;


            }
        }
    }
}

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
        double dorate=7.0;
        double eurate=11.0;
        double corate=500.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

        Intent intent=getIntent();
        dorate=intent.getDoubleExtra("dorate",7.0);
        eurate=intent.getDoubleExtra("eurate",11.0);
        corate=intent.getDoubleExtra("corate",500.0);

        /*Bundle bundle=this.getIntent().getExtras();
        dorate=bundle.getDouble("dorate");
        eurate=bundle.getDouble("eurate");
        corate=bundle.getDouble("corate");*/

        Log.i("RateActivity  onCreat", "dollar rate --->"+dorate);
        Log.i("RateActivity  onCreat", "euro rate--->"+eurate);
        Log.i("RateActivity  onCreat", "con rate--->"+corate);

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
                    startActivityForResult(intent,1);//切换页面到修改汇率
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1&&resultCode==2){
            Bundle bundle=data.getExtras();
            dorate=bundle.getDouble("dorate",1.0);
            eurate=bundle.getDouble("eurate",1.0);
            corate=bundle.getDouble("corate",1.0);

            Log.i("onActivityResult  ", "dollar rate --->"+dorate);
            Log.i("onActivityResult  ", "euro rate--->"+eurate);
            Log.i("onActivityResult  ", "con rate--->"+corate);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

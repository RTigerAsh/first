package cn.edu.swufe.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeRateActivity extends AppCompatActivity {

    EditText edit_dollar,edit_euro,edit_con;
    Button button_returnrate;
    public Double dorate=1.0,eurate=1.0,corate=1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_rate);

        edit_dollar=(EditText)findViewById(R.id.edit_dollar);
        edit_euro=(EditText)findViewById(R.id.edit_euro);
        edit_con=(EditText)findViewById(R.id.edit_con);

        button_returnrate=(Button) findViewById(R.id.button_returnrate);

        MyListener listener = new MyListener();
        edit_dollar.setTag(1);
        edit_dollar.setOnClickListener(listener);
        edit_euro.setTag(2);
        edit_euro.setOnClickListener(listener);
        edit_con.setTag(3);
        edit_con.setOnClickListener(listener);
        button_returnrate.setTag(4);
        button_returnrate.setOnClickListener(listener);
    }



    public class MyListener implements View.OnClickListener {

        public void onClick(View v) {
            int tag =(Integer)v.getTag();

            String strdo=edit_dollar.getText().toString();
            Log.i("ChangeRateActivity", "finished--->"+strdo);
            if (strdo.length()>0)
                dorate= Double.parseDouble(strdo);
            else
                Log.i("ChangeRateActivity", "dollar rate change filled--->"+dorate);
            //dorate= Double.parseDouble(strdo);
            //Log.i("ChangeRateActivity", "dollar rate change finished--->"+dorate);
            String streu=edit_euro.getText().toString();
            if (streu.length()>0)
                eurate= Double.parseDouble(streu);
            else
                Log.i("ChangeRateActivity", "streu rate change filled--->"+dorate);
            String strco=edit_con.getText().toString();
            if (strco.length()>0)
                corate= Double.parseDouble(strco);
            else
                Log.i("ChangeRateActivity", "dollar rate change filled--->"+dorate);

            switch (tag){
                case 1:
                    if (strdo.length()>0){
                        //dorate= Double.parseDouble(strdo);
                        //Log.i("ChangeRateActivity", "finished--->"+strdo);
                        Log.i("ChangeRateActivity", "dollar rate change finished--->"+dorate);
                        //Toast.makeText(ChangeRateActivity.this,"汇率已修改",Toast.LENGTH_SHORT).show();
                    }else {
                        Log.i("ChangeRateActivity", "dollar rate change filled--->"+dorate);
                    }
                    break;
                case 2:
                    if (streu.length()>0){
                        //eurate= Double.parseDouble(streu);
                        Log.i("ChangeRateActivity", "euro rate change finished--->"+eurate);
                        Toast.makeText(ChangeRateActivity.this,"汇率已修改",Toast.LENGTH_SHORT).show();
                    }else {
                        Log.i("ChangeRateActivity", "euro rate change filled--->"+dorate);
                    }
                    break;
                case 3:
                    if (strco.length()>0){
                        //corate=Double.parseDouble(strco);
                        Log.i("ChangeRateActivity", "con rate change finished--->"+corate);
                        Toast.makeText(ChangeRateActivity.this,"汇率已修改", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.i("ChangeRateActivity", "con rate change filled--->"+dorate);
                    }
                    break;
                case 4:
                    Intent intent2 =new Intent(ChangeRateActivity.this,RateActivity.class);

                    /*intent2.putExtra("dorate",dorate);
                    intent2.putExtra("eurate",eurate);
                    intent2.putExtra("corate",corate);*/

                   Bundle bdl=new Bundle();
                   bdl.putDouble("dorate",dorate);
                   bdl.putDouble("eurate",eurate);
                   bdl.putDouble("dcorate",corate);
                   intent2.putExtras(bdl);//传递参数（新汇率）

                    Log.i("ChangeRateActivity", "dollar rate change finished--->"+dorate);
                    Log.i("ChangeRateActivity", "euro rate change finished--->"+eurate);
                    Log.i("ChangeRateActivity", "con rate change finished--->"+corate);

                    setResult(2,intent2);

                    finish();//返回页面到汇率计算

                    break;
            }
        }
    }
}

package cn.edu.swufe.first;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class RateActivity extends AppCompatActivity implements Runnable{

        EditText rmb;
        TextView showrate;
        float dorate=7.0f;
        float eurate=11.0f;
        float corate=500.0f;
        Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);

        Intent intent=getIntent();
        dorate=intent.getFloatExtra("dorate",7.0f);
        eurate=intent.getFloatExtra("eurate",11.0f);
        corate=intent.getFloatExtra("corate",500.0f);

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

        //获取SP里的数据
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dorate=sharedPreferences.getFloat("dorate",7.0f);
        eurate=sharedPreferences.getFloat("eurate",11.0f);
        corate=sharedPreferences.getFloat("corate",500.0f);

        Log.i("spdate  onCreat", "dollar rate --->"+dorate);
        Log.i("spdate  onCreat", "euro rate--->"+eurate);
        Log.i("spdate  onCreat", "con rate--->"+corate);

        //开启子线程
        Thread t=new Thread(this);
        t.start();

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==1){
                    String str=(String) msg.obj;
                    Log.i("hangmessage : ","getMessage msg="+str);
                }
                super.handleMessage(msg);
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.changerate){
            Intent intent =new Intent(RateActivity.this,ChangeRateActivity.class);
            startActivityForResult(intent,1);//切换页面到修改汇率
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
        Log.i("RareActivity run ","子线程开始运行");
        for (int i=1;i<6;i++){
            Log.i("run test","-----i="+i);
            try {
               Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //获取Msg对象  用于返回主线程
        Message msg=handler.obtainMessage();
        msg.what=1;//设置信标
        //Message msg=handler.obtainMessage(1);
        msg.obj="this message from run()  what=1";
        handler.sendMessage(msg);//放回msg堆栈中

        //获取网络数据
        URL rateurl= null;
        try {
            rateurl = new URL("http://www.usd-cny.com/icbc.htm");
            HttpURLConnection http = (HttpURLConnection) rateurl.openConnection();
            InputStream in =http.getInputStream();

            String html=inputStream2String(in);
            Log.i("run getintertmsg", "run: html="+html);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private  String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "gb2312");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();


    }

    public class MyListener implements View.OnClickListener {

        public void onClick(View v) {

            int tag =(Integer)v.getTag();

            String str=rmb.getText().toString();

            switch (tag){

                case 1:

                    if (str.length()>0){
                        float r=Float.parseFloat(str);
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
                        float r=Float.parseFloat(str);
                        r=r*(1/eurate);
                        String bs=String.format("%.2f",r);
                        showrate.setText(bs);
                    }else {
                        Toast.makeText(RateActivity.this,"请输入金额",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:

                    if (str.length()>0){
                        float r=Float.parseFloat(str);
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
            dorate=bundle.getFloat("dorate",1.0f);
            eurate=bundle.getFloat("eurate",1.0f);
            corate=bundle.getFloat("corate",1.0f);

            Log.i("onActivityResult  ", "dollar rate --->"+dorate);
            Log.i("onActivityResult  ", "euro rate--->"+eurate);
            Log.i("onActivityResult  ", "con rate--->"+corate);

            //将新的汇率写入sp
            SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putFloat("dorate",dorate);
            editor.putFloat("eurate",eurate);
            editor.putFloat("corate",corate);
            editor.commit();
            Log.i("onActivityResult  sp", "数据以保存到sharedPreferences");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

package cn.edu.swufe.first;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateListActivity extends ListActivity implements Runnable {
    String  data[]={"PLEASE WAIT......"};
    Handler handler;
    private String logDate = "";
    private final  String DATA_SP_KEY="lastRateDateStr";

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rate_list);

        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
        logDate = sp.getString(DATA_SP_KEY,"");
        Log.i("list", "lastRateDateStr= "+logDate);

        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);

        Thread thread=new Thread(this);
        thread.start();

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==2){
                    List<String> list2=(List<String>)msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);

                }
                super.handleMessage(msg);
            }
        };

    }

    @Override
    public void run() {
        //获取网络数据带回到主线程中
        List<String> relist=new ArrayList<String>();
        String curDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Log.i("run ", "curDateStr:"+curDateStr+"logDate:"+logDate);

        if (curDateStr.equals(logDate)){
            //如果相等，不从网络中获取数据
            Log.i("run", "日期相等，从数据库中获取数据");
            RateManager manager = new RateManager(this);
            for (RateItem item : manager.listAll()){
                relist.add(item.getCurName()+"--->"+item.getCurRate());
            }

        }else {
            Log.i("run", "日期不相等，从网络中获取在线数据");
            Document doc = null;
            try {
                Thread.sleep(2000);
                doc = Jsoup.connect("http://www.boc.cn/sourcedb/whpj/").get();
                Log.i("RateListActivity", "run: "+doc.title());
                Elements tables=doc.getElementsByTag("table");

                Element table2=tables.get(1);
                Log.i("RateListActivity", "run: 从table2获取数据成功");

                //获取<td>中的元素
                Elements tds = table2.getElementsByTag("td");
                List<RateItem> ratelist=new ArrayList<RateItem>();

                for (int i =0;i<tds.size();i+=8){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+5);

                    String text=td1.text();
                    String val=td2.text();
                    Log.i("RateListActivity", "run: "+text+"--->"+val);

                    relist.add(text+"===>"+val);
                    ratelist.add(new RateItem(text,val));
                   /* HashMap<String,String> map = new HashMap<String, String>();
                    map.put("ItemTitle",text);
                    map.put("ItemDetail",val);*/
                }
                //数据写入数据库中
                RateManager manager = new RateManager(this);
                manager.deleteAll();
                manager.addAll(ratelist);

                //记录更新日期
                //写入更新日期
                SharedPreferences sp = getSharedPreferences("myrate",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString(DATA_SP_KEY,curDateStr);
                edit.commit();
                Log.i("run", "更新日期结束:"+curDateStr);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


        //获取Msg对象  用于返回主线程
        Message msg=handler.obtainMessage(2);
        msg.obj=relist;
        handler.sendMessage(msg);//--放回msg堆栈中

    }


}

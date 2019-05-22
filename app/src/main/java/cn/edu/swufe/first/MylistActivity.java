package cn.edu.swufe.first;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MylistActivity extends ListActivity implements Runnable,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    Handler handler;
    private List<HashMap<String,String>> listItems;//存放文字、图片信息
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();

        /*MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,listItems);
        this.setListAdapter(myAdapter);*/
        this.setListAdapter(listItemAdapter);

        Thread thread=new Thread(this);
        thread.start();

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==2){
                    listItems= (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(MylistActivity.this,listItems,//listItems数据源
                            R.layout.list_item,//listItems的XML布局实现
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail}
                    );
                    setListAdapter(listItemAdapter);

                }
                super.handleMessage(msg);
            }
        };
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);

    }


    private void initListView(){
        listItems = new ArrayList<HashMap<String,String>>();
        for (int i = 0;i<10;i++){
            HashMap<String,String> map= new HashMap<String, String>();
            map.put("ItemTitle","Rate"+i);//标题文字
            map.put("ItemDetail","detail"+i);//详情描述
            listItems.add(map);
        }
        //生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this,listItems,//listItems数据源
                R.layout.list_item,//listItems的XML布局实现
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
                );
    }

    @Override
    public void run() {
        //获取网络数据带回到主线程中
        List<HashMap<String,String>> relist=new ArrayList<HashMap<String,String>>();

        Document doc = null;
        try {
            Thread.sleep(100);
            doc = Jsoup.connect("http://www.boc.cn/sourcedb/whpj/").get();
            Log.i("RateListActivity", "run: "+doc.title());
            Elements tables=doc.getElementsByTag("table");

            Element table2=tables.get(1);
            Log.i("RateListActivity", "run: 从table2获取数据成功");

            //获取<td>中的元素
            Elements tds = table2.getElementsByTag("td");
            for (int i =0;i<tds.size();i+=8){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);

                String text=td1.text();
                String val=td2.text();
                Log.i("RateListActivity", "run: "+text+"--->"+val);


                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemTitle",text);
                map.put("ItemDetail",val);
                relist.add(map);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取Msg对象  用于返回主线程
        Message msg=handler.obtainMessage(2);
        msg.obj=relist;
        handler.sendMessage(msg);//--放回msg堆栈中

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String,String> map= (HashMap<String, String>) getListView().getItemAtPosition(position);
        String titlestr=map.get("ItemTitle");
        String detailstr=map.get("ItemDetail");
//        TextView title =(TextView)view.findViewById(R.id.itemTitle);
//        TextView detail =(TextView)view.findViewById(R.id.itemDetail);
//        String title1= String.valueOf(title.getText());
//        String detail2= String.valueOf(title.getText());

        //打开新的页面传入参数
        Intent rateCalc = new Intent(MylistActivity.this, RateCalcActivity.class);
        rateCalc.putExtra("title",titlestr);
        rateCalc.putExtra("rate",Float.parseFloat(detailstr));
        startActivity(rateCalc);


       /* //构建对话框实现汇率计算
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_rate_calc,null);
        customizeDialog.setTitle("汇率计算");
        customizeDialog.setView(dialogView);
        final float rate=Float.parseFloat(detailstr);
        EditText inp;
        *//*TextView tv = (TextView)findViewById(R.id.text_ratecalc_title);
        tv.setText(titlestr);*//*
        inp=(EditText)findViewById(R.id.edit_ratecalc);
        inp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView show=(TextView)MylistActivity.this.findViewById(R.id.text_ratecalc_result);
                if (s.length()>0){
                    float val = Float.parseFloat(s.toString());
                    String bs=String.format("%.2f",(100/rate*val));
                    show.setText(val+"RMB===>"+bs);
                }else {
                    show.setText("");
                }

            }
        });*/


        /*customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                (EditText) dialogView.findViewById(R.id.edit_ratecalc);
                        Toast.makeText(MylistActivity.this,
                                edit_text.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        customizeDialog.show();*/



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i("长按--", "onLongClick: position="+position);
        //删除操作
        /*listItems.remove(position);
        listItemAdapter.notifyDataSetChanged();*/
        //构造对话框删除操作
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("MylistActivity", "onClick: 对话框事件处理");
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.show();
        return true;
    }
}

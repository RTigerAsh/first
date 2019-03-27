package cn.edu.swufe.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        but.setOnClickListener(this);


        Button j3=findViewById(R.id.j3);
        Button j2=findViewById(R.id.j2);
        Button j1=findViewById(R.id.j1);
        Button clear=findViewById(R.id.clear);

    }

    public void onClick(View v) {

        double inp1=Double.parseDouble(input.getText().toString());
        double fs=1.8*inp1+32;

        bs=String.format("%.2f",fs);//转换为两位小数

        out.setText("结果为："+bs);
        Log.i("main", "click finished");

    }
}

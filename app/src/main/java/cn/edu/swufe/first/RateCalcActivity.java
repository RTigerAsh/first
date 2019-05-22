package cn.edu.swufe.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class RateCalcActivity extends AppCompatActivity {
    String TAG="rateCalc";
    float rate=0f;
    EditText inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calc);

        String title = getIntent().getStringExtra("title");
        rate=getIntent().getFloatExtra("rate",0f);
        Log.i(TAG, "onCreate: title:-->"+title);
        Log.i(TAG, "onCreate: rate:-->"+rate);

        ((TextView)findViewById(R.id.text_ratecalc_title)).setText(title);
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
                TextView show=(TextView)RateCalcActivity.this.findViewById(R.id.text_ratecalc_result);
                if (s.length()>0){
                    float val = Float.parseFloat(s.toString());
                    String bs=String.format("%.2f",(100/rate*val));
                    show.setText(val+"RMB===>"+bs);
                }else {
                    show.setText("");
                }

            }
        });

    }
}

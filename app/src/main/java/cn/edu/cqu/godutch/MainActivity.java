package cn.edu.cqu.godutch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String amount;
    EditText totalAmount, totalPeo;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalAmount = (EditText) findViewById(R.id.editTextOfBill);
        totalPeo = (EditText) findViewById(R.id.editTextOfPeo);
        if(totalAmount.equals("")||totalPeo.equals(""))
        {Toast.makeText(getBaseContext(), "At least type something?!", Toast.LENGTH_SHORT).show();
        }

        totalAmount.setFilters(new InputFilter[]{lengthFilter});

    }


    public void calculate(View v) {
        double total = Double.parseDouble(totalAmount.getText().toString());
        double persons = Double.parseDouble(totalPeo.getText().toString());
        Double share = total / persons;
        share = Double.valueOf(Math.round(share * 100));
        share = share / 100;

        if(persons == 0)
            { Toast.makeText(getBaseContext(), "please enter consumption", Toast.LENGTH_SHORT).show();}
        else {
            Toast.makeText(getBaseContext(), "Amount is:" + share, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            //设置Intent的class属性，跳转到SecondActivity
            intent.setClass(MainActivity.this, paylayout.class);
            intent.putExtra("payEach", share.toString());
            //启动Activity
            startActivity(intent);
        }
    }

    /**
     * 输入框小数的位数
     */
    private static final int DECIMAL_DIGITS = 1;

    private InputFilter lengthFilter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // source:当前输入的字符
            // start:输入字符的开始位置
            // end:输入字符的结束位置
            // dest：当前已显示的内容
            // dstart:当前光标开始位置
            // dent:当前光标结束位置
            Log.i("", "source=" + source + ",start=" + start + ",end=" + end
                    + ",dest=" + dest.toString() + ",dstart=" + dstart
                    + ",dend=" + dend);
            if (dest.length() == 0 && source.equals(".")) {
                return "0.";
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                if (dotValue.length() == DECIMAL_DIGITS) {
                    return "";
                }
            }
            return null;
        }

    };

}

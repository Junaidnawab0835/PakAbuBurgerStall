package com.example.pakabuburgerstall;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    TextView clear_btn;
    EditText cash_amount_edtxt;
    RadioGroup radioGroup;
    LinearLayout cash_layout;
    double total_payment = 0.0;
    TextView change_txt;
    TextView save_order_btn;
    String payment_method ="Cash";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //
        Intent intent = getIntent();
        total_payment = Double.parseDouble(intent.getStringExtra("totalpayment"));
        //
        clear_btn = findViewById(R.id.clear_text);
        change_txt = findViewById(R.id.change_txt);
        cash_amount_edtxt = findViewById(R.id.user_amount_edtxt);
        radioGroup = findViewById(R.id.radiogroup);
        cash_layout = findViewById(R.id.layout_cash);
        save_order_btn = findViewById(R.id.saveorder_text);

        clear_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                cash_amount_edtxt.setText("");
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId==R.id.cash_radiobtn)
                {
                    payment_method = "Cash";
                    cash_layout.setVisibility(View.VISIBLE);
                }
                else {
                    payment_method = "Card";
                    cash_layout.setVisibility(View.GONE);
                }
            }
        });
        cash_amount_edtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    if(!cash_amount_edtxt.getText().toString().isEmpty())
                    {
                       double user_cash = Double.parseDouble(cash_amount_edtxt.getText().toString());
                       if(user_cash>=total_payment) {
                           change_txt.setText((user_cash - total_payment) + " ");
                       }
                       else
                       {
                           change_txt.setText("0.0");
                       }

                    }
                    else
                    {
                        change_txt.setText("0.0");
                    }
            }
        });

        save_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(PaymentActivity.this);
                dbHelper.AddOrder(String.valueOf(total_payment),payment_method);
                Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}
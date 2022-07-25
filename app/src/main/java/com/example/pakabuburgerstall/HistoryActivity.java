package com.example.pakabuburgerstall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    ImageView start_date_btn;
    TextView start_date_txt;
    ImageView end_date_btn;
    TextView end_date_txt;
    private int mYear, mMonth, mDay;
    TextView show_history_btn;
    List<Orders> items;
    RecyclerView history_recylerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        start_date_btn = findViewById(R.id.btn_start_date);
        start_date_txt = findViewById(R.id.start_date_txt);
        end_date_btn = findViewById(R.id.btn_end_date);
        end_date_txt = findViewById(R.id.end_date_txt);
        history_recylerview = findViewById(R.id.history_recyclerview);
        show_history_btn = findViewById(R.id.show_history_btn);
        start_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                start_date_txt.setText(mYear + "-" + (monthOfYear + 1) + "-" +dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        end_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                end_date_txt.setText(mYear + "-" + (monthOfYear + 1) + "-" +dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        show_history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items = new ArrayList<>();
                Orders orders;
                DBHelper dbHelper = new DBHelper(HistoryActivity.this);
               Cursor cursor = dbHelper.history(start_date_txt.getText().toString(),end_date_txt.getText().toString());
             //  Cursor cursor = dbHelper.FetchOrder();
                while (cursor.moveToNext())
                {
                   Log.d("test123",cursor.getString(cursor.getColumnIndex("date")));
                    String date = cursor.getString(cursor.getColumnIndex("date"));
                    String order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                    String total_payment = cursor.getString(cursor.getColumnIndex("total_payment"));
                    String payment_method = cursor.getString(cursor.getColumnIndex("payment_method"));
                    orders = new Orders(order_id,total_payment,payment_method,date);
                    items.add(orders);
                }
                HistoryCustomAdapter adapter = new HistoryCustomAdapter(HistoryActivity.this,items);
                history_recylerview.setLayoutManager(new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                history_recylerview.setAdapter(adapter);
            }
        });
    }
}
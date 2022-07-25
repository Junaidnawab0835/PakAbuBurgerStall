package com.example.pakabuburgerstall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import  com.example.pakabuburgerstall.Interface.onValueUpdate;

public class MainActivity extends AppCompatActivity implements onValueUpdate{
    RecyclerView main_recyclerView;
    List<DishItem> items;
    CustomAdapter myAdapter;
    TextView txtTotalPrice;
    TextView next_btn;
    TextView history_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        items.add(new DishItem("Chicken Patty",4,"1",0,R.drawable.burger));
        items.add(new DishItem("Chicken Special Patty",5,"2",0,R.drawable.burger));
        items.add(new DishItem("Imported Lamb Party",8,"3",0,R.drawable.burger));
        items.add(new DishItem("Imported Lamb Special Party",10,"4",0,R.drawable.burger));

        txtTotalPrice=findViewById(R.id.txtTotalPrice);
        next_btn = findViewById(R.id.next_btn);
        history_btn = findViewById(R.id.hsitory_btn);
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HistoryActivity.class);
                startActivity(intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTotalPrice.getText().toString().equals("0.0"))
                {
                    Toast.makeText(MainActivity.this,"Order is empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("totalpayment", txtTotalPrice.getText().toString());
                    startActivity(intent);
                }
            }
        });

        checkBox_member = findViewById(R.id.checkbox_member);
        checkBox_member.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onValueUpdate();
            }
        });
        main_recyclerView = findViewById(R.id.recyclerview);
        myAdapter = new CustomAdapter(this,items,this);
        main_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        main_recyclerView.setAdapter(myAdapter);
    }


    @Override
    public  void onValueUpdate()
    {
        double totalprice=0;
        for(DishItem item:items)
        {
            totalprice += (item.getPrice() * item.getQuantity());
        }
        txtTotalPrice.setText(String.valueOf(totalprice));

    }

}
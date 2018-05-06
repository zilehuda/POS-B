package com.example.zilay.pos_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zilay.pos_b.models.Orders;

public class OrderActivity extends AppCompatActivity {

    Orders order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order = new Orders();
        Intent intent = getIntent();
        order.setPid(intent.getStringExtra("id"));
        order.setQty(Integer.parseInt(intent.getStringExtra("qty")));

    }

    public void Proceed(View view) {
    }
}

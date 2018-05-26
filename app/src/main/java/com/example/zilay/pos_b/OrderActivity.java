package com.example.zilay.pos_b;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilay.pos_b.models.Orders;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    TextView pname;
    TextView total;
    AutoCompleteTextView qty;
    Orders order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order = new Orders();
        Intent intent = getIntent();
        final int price = Integer.parseInt(intent.getStringExtra("price"));
        int pqty = Integer.parseInt(intent.getStringExtra("qty"));
        order.setPid(Integer.parseInt(intent.getStringExtra("productId")));
        qty = (AutoCompleteTextView) findViewById(R.id.productqty);
        total = (TextView) findViewById(R.id.total);
        pname = (TextView) findViewById(R.id.tvpname);
        pname.setText(intent.getStringExtra("pname").toString());
        qty.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    int t = Integer.parseInt(qty.getText().toString())*price;
                    order.setQty(Integer.parseInt(qty.getText().toString()));
                    order.setTotal(t);
                    total.setText(Integer.toString(t));
                }
            }
        });

    }

    public void Proceed(View view) {

        final JsonObject productInfo = new JsonObject();
        productInfo.addProperty("productId",order.getPid());
        productInfo.addProperty("qty",order.getQty());
        productInfo.addProperty("total",order.getTotal());
        //Toast.makeText(this,productInfo.toString(),Toast.LENGTH_SHORT).show();
        SendToServer(productInfo);
    }

    public void SendToServer(JsonObject obj)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Orders> call = apiInterface.PostOrder(obj);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Loading");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {

                if(response.isSuccessful())
                {
                    progressDoalog.dismiss();
                    Toast.makeText(OrderActivity.this,"Order Completed",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OrderActivity.this,  FirstActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(OrderActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package com.example.zilay.pos_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.zilay.pos_b.models.Products;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    AutoCompleteTextView name;
    AutoCompleteTextView qty;
    AutoCompleteTextView price;
    AutoCompleteTextView vendorid;
    Products product;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        product = new Products();
        name = (AutoCompleteTextView) findViewById(R.id.productname);
        qty = (AutoCompleteTextView) findViewById(R.id.productqty);
        price = (AutoCompleteTextView) findViewById(R.id.productprice);
        vendorid = (AutoCompleteTextView) findViewById(R.id.vendorid);

    }

    public void SubmitToApi(View view) {

        final JsonObject productInfo = new JsonObject();
        productInfo.addProperty("name",name.getText().toString());
        productInfo.addProperty("qty",Integer.valueOf(qty.getText().toString()));
        productInfo.addProperty("price",Integer.valueOf(price.getText().toString()));
        productInfo.addProperty("vendid",Integer.valueOf(vendorid.getText().toString()));
        SendToServer(productInfo);
    }

    public void SendToServer(JsonObject productInfo)
    {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Products> call = apiInterface.postProducts(productInfo);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });



    }

    public void ScndAct(View view) {
        Intent intent = new Intent(this,InventoryBarcodeActivity.class);
        startActivity(intent);
    }
}

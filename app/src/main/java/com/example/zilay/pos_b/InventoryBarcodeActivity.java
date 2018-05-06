package com.example.zilay.pos_b;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.zilay.pos_b.models.Orders;
import com.example.zilay.pos_b.models.Products;
import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        //Log.v("bar", rawResult.getText()); // Prints scan results
        //Toast.makeText(this,rawResult.getText().toString(),Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,rawResult.getBarcodeFormat().toString(),Toast.LENGTH_SHORT).show();
        SendToServer(rawResult.getText().toString());
        //Log.v("bar-1", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        setContentView(R.layout.activity_main);
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }


    public  void SendToServer(final String id)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Products>> call = apiInterface.getSingleProduct(id);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Getting Product Details");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(response.isSuccessful())
                {
                    progressDoalog.dismiss();
                    Toast.makeText(InventoryBarcodeActivity.this,"yes",Toast.LENGTH_SHORT).show();
                    List<Products> pr = response.body();
                    Products p = pr.get(0);
                    Toast.makeText(InventoryBarcodeActivity.this,pr.get(0).getProductname(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InventoryBarcodeActivity.this,OrderActivity.class);
                    intent.putExtra("productId",id);
                    intent.putExtra("qty",p.getProductqty());
                    intent.putExtra("price",p.getPrice());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(InventoryBarcodeActivity.this,"no",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
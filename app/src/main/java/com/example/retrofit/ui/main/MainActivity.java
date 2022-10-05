package com.example.retrofit.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.data.ProductInterface;
import com.example.retrofit.pojo.Product;
import com.example.retrofit.ui.settings.SettingsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    ProductViewModel productViewModel;
    ProgressBar progressBar;
    private static final String SEPARATOR = ".json";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // int status = getIntent().getIntExtra("condition", 0);
        String brand = getIntent().getStringExtra("extra_brand");
        String product = getIntent().getStringExtra("extra_product_name");

        if(brand == null && product == null)
        {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
            productViewModel.getProducts();
        }
        else if (!TextUtils.isEmpty(brand) && !TextUtils.isEmpty(product))
        {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
            productViewModel.getCompleteQuery(brand, product);
        }
        else if (!TextUtils.isEmpty(brand))
        {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
            productViewModel.getBrandQuery(brand);
        }
        else if (!TextUtils.isEmpty(product))
        {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
            productViewModel.getProductTypeQuery(product);
        }


        Log.d(TAG, "onCreate: DELIVERED?"+brand+ product);
/**
        if(status == 0)
        {
            productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
            productViewModel.getProducts();
        }

        switch (status)
        {
            case 1:
                productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
                productViewModel.getCompleteQuery(brand, product);
                status = 0;
                break;
            case 2:
                productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
                productViewModel.getBrandQuery(brand);
                status = 0;
                break;
            case 3:
                productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
                productViewModel.getProductTypeQuery(product);
                status = 0;
                break;
            case 4:
                productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
                productViewModel.getProducts();
                status = 0;
        }
**/
        progressBar = findViewById(R.id.progress_bar);

        TextView noInternet = findViewById(R.id.empty_view);

        RecyclerView productRecycler = findViewById(R.id.product_recycler);
        final ProductAdapter productAdapter = new ProductAdapter();
        productRecycler.setLayoutManager(new LinearLayoutManager(this));
        productRecycler.setAdapter(productAdapter);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
        {
            productViewModel.loading.observe(this, new Observer<Boolean>()
            {
                @Override
                public void onChanged(Boolean aBoolean)
                {
                    progressBar(productViewModel.loading);
                }
            });

            productViewModel.productMutableLiveData.observe(this, new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products)
                {
                    productAdapter.setList(products, new ProductAdapter.itemClickListener()
                    {
                        @Override
                        public void onItemClick(Product product)
                        {
                            websiteIntent(product.getProductApiUrl());
                        }
                    });
                }
            });
        }
        else
            {
                progressBar.setVisibility(View.GONE);
                noInternet.setText(R.string.no_internet_connection);
                noInternet.setVisibility(View.VISIBLE);
            }


/**
 productCall.enqueue(new Callback<Product>() {
@Override public void onResponse(Call<Product> call, Response<Product> response) {

Picasso.with(MainActivity.this).load(response.body().getImageLink()).into(test);

}

@Override public void onFailure(Call<Product> call, Throwable t) {


}
});
 **/
    }

    public Intent websiteIntent(String url)
    {
        String link = null;
        if(url.contains(SEPARATOR))
        {
            String[] parts = url.split(SEPARATOR);
            link = parts[0];
        }
        Uri uri = Uri.parse(link);
        Intent wesiteIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(wesiteIntent);
        return wesiteIntent;
    }

    public ProgressBar progressBar(MutableLiveData<Boolean> loading)
    {
        if(loading.getValue() == false)
        {
            progressBar.setVisibility(View.GONE);
        }
        return progressBar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.search_settings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

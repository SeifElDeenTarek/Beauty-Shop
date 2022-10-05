package com.example.retrofit.data;

import android.util.Log;

import com.example.retrofit.pojo.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductClient
{
    private static final String TAG = "ProductClient";
    private static final String BASE_URL= "https://makeup-api.herokuapp.com/api/v1/";
    private ProductInterface productInterface;
    private static ProductClient INSTANCE;

    public ProductClient()
    {
        Retrofit retrofitALL = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();


        productInterface = retrofitALL.create(ProductInterface.class);
    }

    public static ProductClient getINSTANCE()
    {
        if(null == INSTANCE)
        {
            INSTANCE = new ProductClient();
        }
        return INSTANCE;
    }

    public Observable<List<Product>> getProducts()
    {
        return productInterface.getProducts();
    }

    public Observable<List<Product>> getBrandQuery(String brand)
    {
        return productInterface.getBrandQuery(brand);
    }

    public Observable<List<Product>> getProductTypeQuery(String productType)
    {
        return productInterface.getProductTypeQuery(productType);
    }

    public Observable<List<Product>> getPriceGreaterThanQuery(int priceGreaterThan)
    {
        return productInterface.getPriceGreaterThanQuery(priceGreaterThan);
    }

    public Observable<List<Product>> getCompleteQuery(String brand, String productType)
    {
        return productInterface.getCompleteQuery(brand, productType);
    }
}

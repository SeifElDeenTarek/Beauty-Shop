package com.example.retrofit.data;

import com.example.retrofit.pojo.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductInterface
{
    @GET("products.json")
    public Observable<List<Product>> getProducts();

    @GET("products.json")
    public Observable<List<Product>> getBrandQuery(@Query("brand") String brand);

    @GET("products.json")
    public Observable<List<Product>> getProductTypeQuery(@Query("product_type") String productType);

    @GET("products.json")
    public Observable<List<Product>> getPriceGreaterThanQuery(@Query("price_greater_than") int priceGreaterThan);

    @GET("products.json")
    public Observable<List<Product>> getCompleteQuery(@Query("brand") String brand,
                                                      @Query("product_type") String productType);

    // @GET("products/{brand}")
   //  public Call<Product> getProducts(@Path("brand") String brand);
/**
    @GET("products.json/{brand}")
    public Call<Product> getBrand(@Path("brand") String brand);

    @GET("products.json/{product_type}")
    public Call<List<Product>> getProductType(@Path("product_type") String productType);

    @GET("products.json/{product_category}")
    public Call<List<Product>> getProductCategory(@Path("product_category") String productCategory);
    **/
}
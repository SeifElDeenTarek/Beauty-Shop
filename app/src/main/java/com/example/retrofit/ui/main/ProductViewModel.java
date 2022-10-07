package com.example.retrofit.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.data.ProductClient;
import com.example.retrofit.pojo.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel
{
    private static final String TAG = "ProductViewModel";

    MutableLiveData<List<Product>> productMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> products = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void getProducts()
    {
        loading.setValue(true);
        Observable observable = ProductClient.getINSTANCE().getProducts().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

        Observer<List<Product>> observer = new Observer<List<Product>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Product> productList)
            {
                productList = productList.subList(50, 500);
                productMutableLiveData.setValue(productList);
                Log.d(TAG, "3DD" + productList.size());
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete()
            {
            }
        };

        observable.subscribe(observer);
    }

    public void getBrandQuery(String brand)
    {
        loading.setValue(true);
        Observable observable = ProductClient.getINSTANCE().getBrandQuery(brand).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

        Observer<List<Product>> observer = new Observer<List<Product>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Product> productList)
            {
                productMutableLiveData.setValue(productList);
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete()
            {
            }
        };

        observable.subscribe(observer);
    }

    public void getProductTypeQuery(String product)
    {
        loading.setValue(true);
        Observable observable = ProductClient.getINSTANCE().getProductTypeQuery(product).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

        Observer<List<Product>> observer = new Observer<List<Product>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Product> productList)
            {
                productMutableLiveData.setValue(productList);
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete()
            {
            }
        };

        observable.subscribe(observer);
    }

    public void getPriceGreaterThanQuery(int price)
    {
        loading.setValue(true);
        Observable observable = ProductClient.getINSTANCE().getPriceGreaterThanQuery(price).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

        Observer<List<Product>> observer = new Observer<List<Product>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Product> productList)
            {
                productMutableLiveData.setValue(productList);
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete()
            {
            }
        };

        observable.subscribe(observer);
    }

    public void getCompleteQuery(String brand, String productType)
    {
        loading.setValue(true);
        Observable observable = ProductClient.getINSTANCE().getCompleteQuery(brand, productType).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

        Observer<List<Product>> observer = new Observer<List<Product>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Product> productList)
            {
                productMutableLiveData.setValue(productList);
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete()
            {
            }
        };

        observable.subscribe(observer);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

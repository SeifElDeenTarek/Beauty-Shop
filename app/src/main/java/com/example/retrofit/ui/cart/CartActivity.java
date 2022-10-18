package com.example.retrofit.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofit.R;
import com.example.retrofit.room.RoomDataBase;
import com.example.retrofit.room.RoomProduct;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity
{
    Context context;
    private static final String TAG = "CartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = getApplicationContext();

        String name = getIntent().getStringExtra("product_name");
        String brand = getIntent().getStringExtra("product_brand");
        String price = getIntent().getStringExtra("product_price");
        String imageLink = getIntent().getStringExtra("product_image_link");
        String productUrl = getIntent().getStringExtra("product_url");
        int id = getIntent().getIntExtra("product_id", 1);

        Log.d(TAG, "Room: "+ id+name+brand+price+imageLink+productUrl);

        RecyclerView cartRecycler = findViewById(R.id.room_recycler);
        final CartAdapter cartAdapter = new CartAdapter();
        cartRecycler.setLayoutManager(new LinearLayoutManager(this));
        cartRecycler.setAdapter(cartAdapter);

        RoomDataBase roomDataBase = RoomDataBase.getInstance(context);

        if(name != null)
        {
            Log.d(TAG, "Room2: "+ id+name+brand+price+imageLink+productUrl);
            roomDataBase.roomDao().insertProduct(new RoomProduct(id, name, brand, price, imageLink))
                    .subscribeOn(Schedulers.computation())
                    .subscribe(new CompletableObserver()
                    {
                        @Override
                        public void onSubscribe(Disposable d) {


                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }

        roomDataBase.roomDao().getProducts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RoomProduct>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<RoomProduct> roomProducts) {
                        cartAdapter.setList(roomProducts);
                        cartAdapter.notifyDataSetChanged();
                        Log.d(TAG, "Room3: "+ id+name+brand+price+imageLink+productUrl);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    };
}
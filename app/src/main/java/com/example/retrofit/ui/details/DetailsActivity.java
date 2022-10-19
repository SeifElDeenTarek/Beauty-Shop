package com.example.retrofit.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.ui.cart.CartActivity;

import static android.content.ContentValues.TAG;

public class DetailsActivity extends AppCompatActivity
{
    private static final String SEPARATOR = ".json";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView itemName, itemBrand, itemPrice, itemDesc;
        Button itemBuy, itemCart;
        ImageView itemImage = findViewById(R.id.details_image);

        String name = getIntent().getStringExtra("product_name");
        String brand = getIntent().getStringExtra("product_brand");
        String price = getIntent().getStringExtra("product_price");
        String desc = getIntent().getStringExtra("product_desc");
        String imageLink = getIntent().getStringExtra("product_image_link");
        String productUrl = getIntent().getStringExtra("product_url");
        int id = getIntent().getIntExtra("product_id", 1);

        Log.d(TAG, "DESC" + desc);

        itemName = findViewById(R.id.details_name);
        itemBrand = findViewById(R.id.details_brand);
        itemPrice = findViewById(R.id.details_price);
        itemDesc = findViewById(R.id.details_desc);
        itemBuy = findViewById(R.id.website_buy);
        itemCart = findViewById(R.id.room_cart);

        itemName.setText(name);
        itemBrand.setText(brand);
        itemPrice.setText(price);
        itemDesc.setText(desc);

        Glide.with(itemImage.getContext())
                .load(imageLink)
                .override(150, 150)
                .into(itemImage);

        itemCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                roomIntent(id, name, brand, price, imageLink, productUrl);
            }
        });

        itemBuy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BuyIntent(productUrl);
            }
        });
    }

    public Intent BuyIntent(String url)
    {
        String link = null;
        if(url.contains(SEPARATOR))
        {
            String[] parts = url.split(SEPARATOR);
            link = parts[0];
        }
        Uri uri = Uri.parse(link);
        Intent buyIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(buyIntent);
        return buyIntent;
    }

    public Intent roomIntent(int id, String name, String brand, String price, String imageLink, String productURL)
    {
        Intent roomIntent = new Intent(this, CartActivity.class);
        roomIntent.putExtra("product_id", id);
        roomIntent.putExtra("product_name", name);
        roomIntent.putExtra("product_brand", brand);
        roomIntent.putExtra("product_price", price);
        roomIntent.putExtra("product_image_link", imageLink);
        roomIntent.putExtra("product_url", productURL);
        startActivity(roomIntent);
        return roomIntent;
    }
}

package com.example.retrofit.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.pojo.Product;
import com.example.retrofit.ui.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{
    @Nullable
    private itemClickListener itemClickListener;
    private List<Product> productList = new ArrayList<>();

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position)
    {
        holder.productName.setText(productList.get(position).getName());
        holder.productBrand.setText(productList.get(position).getBrand());
        holder.productPrice.setText(productList.get(position).getPrice()+"$");
        Uri uri = Uri.parse(productList.get(position).getImageLink());
        //holder.productImage.setImageURI(null);
        //holder.productImage.setImageURI(uri);

        Glide.with(holder.productImage.getContext())
                .load(uri)
                .override(150, 150)
                .into(holder.productImage);

        holder.itemView.setOnClickListener(v ->{
            itemClickListener.onItemClick(productList.get(position));
        });
    }

    @Override
    public int getItemCount()
    {
        return productList.size();
    }

    public void setList(List<Product> productList, ProductAdapter.itemClickListener itemClickListener)
    {
        this.productList = productList;
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public interface itemClickListener
    {
        void onItemClick(Product product);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName, productBrand, productPrice;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productBrand = itemView.findViewById(R.id.product_brand);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_picture);

        }
    }
}

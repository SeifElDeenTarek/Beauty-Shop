package com.example.retrofit.ui.cart;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.room.RoomProduct;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{
    private List<RoomProduct> roomProducts = new ArrayList<>();

    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new CartViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position)
    {
        holder.productName.setText(roomProducts.get(position).getName());
        holder.productBrand.setText(roomProducts.get(position).getBrand());
        holder.productPrice.setText(roomProducts.get(position).getPrice()+"$");
        Uri uri = Uri.parse(roomProducts.get(position).getImageLink());
        //holder.productImage.setImageURI(null);
        //holder.productImage.setImageURI(uri);

        Glide.with(holder.productImage.getContext())
                .load(uri)
                .override(150, 150)
                .into(holder.productImage);
    }

    @Override
    public int getItemCount()
    {
        return roomProducts.size();
    }

    public void setList(List<RoomProduct> roomProducts)
    {
        this.roomProducts.clear();
        this.roomProducts = roomProducts;
        notifyDataSetChanged();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName, productBrand, productPrice;
        ImageView productImage;

        public CartViewHolder(@NonNull View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name_room);
            productBrand = itemView.findViewById(R.id.product_brand_room);
            productPrice = itemView.findViewById(R.id.product_price_room);
            productImage = itemView.findViewById(R.id.product_image_room);
        }
    }
}

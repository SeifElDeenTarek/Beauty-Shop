package com.example.retrofit.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface ProductDao
{
    @Insert
    Completable insertProduct(RoomProduct roomProduct);

    @Query("select * from products_table")
    Observable<List<RoomProduct>> getProducts();
}

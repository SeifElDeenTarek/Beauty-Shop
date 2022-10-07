package com.example.retrofit.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = RoomProduct.class, version = 1)

public abstract class RoomDataBase extends RoomDatabase
{
    private static RoomDataBase instance;
    public abstract ProductDao roomDao();

    public static synchronized RoomDataBase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,RoomDataBase.class, "products_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

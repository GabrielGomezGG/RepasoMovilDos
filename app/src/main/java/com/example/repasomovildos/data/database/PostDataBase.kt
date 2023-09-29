package com.example.repasomovildos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDataBase : RoomDatabase(){

    abstract fun postDao() : PostDao
}
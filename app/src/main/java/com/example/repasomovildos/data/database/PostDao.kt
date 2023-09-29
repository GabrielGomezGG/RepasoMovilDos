package com.example.repasomovildos.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity")
    fun getPosts() : Flow<List<PostEntity>>

    @Query("SELECT * FROM PostEntity")
    fun getAllPosts() : List<PostEntity>

    @Query("SELECT * FROM PostEntity WHERE id = :id")
    suspend fun getPostById(id : Int) : PostEntity

    @Delete
    suspend fun deletePost(post : PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post : List<PostEntity>)

}
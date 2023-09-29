package com.example.repasomovildos.data

import com.example.repasomovildos.data.database.PostDao
import com.example.repasomovildos.data.database.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PostDBRepository {

    suspend fun getPost() : Flow<List<PostEntity>>

    suspend fun getAllPost() : List<PostEntity>

    suspend fun getPostById(id : Int) : PostEntity

    suspend fun deletePost(post : PostEntity)

    suspend fun insertPosts(post : List<PostEntity>)

}

class PostDBRepositoryImpl @Inject constructor(
    private val postDao: PostDao
): PostDBRepository{
    override suspend fun getPost(): Flow<List<PostEntity>> {
        return postDao.getPosts()
    }

    override suspend fun getAllPost(): List<PostEntity> {
        return postDao.getAllPosts()
    }

    override suspend fun getPostById(id: Int): PostEntity {
        return postDao.getPostById(id)
    }

    override suspend fun deletePost(post: PostEntity) {
        postDao.deletePost(post)
    }

    override suspend fun insertPosts(post: List<PostEntity>) {
        postDao.insertPosts(post)
    }

}
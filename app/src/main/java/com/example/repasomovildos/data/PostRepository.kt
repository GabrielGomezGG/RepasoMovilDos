package com.example.repasomovildos.data

import com.example.repasomovildos.data.api.ApiService
import com.example.repasomovildos.data.mapper.toPost
import com.example.repasomovildos.data.model.Post
import javax.inject.Inject

interface PostRepository {
    suspend fun getPost() : List<Post>
}

class IPostRepository @Inject constructor(
    private val apiService: ApiService
) : PostRepository{
    override suspend fun getPost(): List<Post> {
        return apiService.getPosts().body()!!.map { it.toPost() }
    }

}


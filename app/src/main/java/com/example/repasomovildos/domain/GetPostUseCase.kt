package com.example.repasomovildos.domain

import com.example.repasomovildos.data.PostDBRepository
import com.example.repasomovildos.data.PostRepository
import com.example.repasomovildos.data.api.PostResponse
import com.example.repasomovildos.data.database.PostEntity
import com.example.repasomovildos.data.mapper.toPostEntity
import com.example.repasomovildos.data.mapper.toPostResponse
import com.example.repasomovildos.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postDBRepository: PostDBRepository
) {

    suspend operator fun invoke() : Flow<List<PostEntity>>{

        var posts = emptyList<PostResponse>()

        val flowPost = flow{
            if (postDBRepository.getAllPost().isEmpty()) {
                posts = postRepository.getPost().map {
                    it.toPostResponse()
                }

                postDBRepository.insertPosts(posts.map { it.toPostEntity() })
            }
            emit(posts)
        }


        val postsDB = postDBRepository.getPost().combine(flowPost){
                postDB, post -> postDB
        }

        return postsDB

    }
}
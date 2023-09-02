package com.example.repasomovildos.data.mapper

import com.example.repasomovildos.data.api.PostResponse
import com.example.repasomovildos.data.model.Post

fun Post.toPostResponse() : PostResponse {
    return PostResponse(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}

fun PostResponse.toPost() : Post {
    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
package com.teste.testegfx.data.remote;

import com.teste.testegfx.data.model.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}

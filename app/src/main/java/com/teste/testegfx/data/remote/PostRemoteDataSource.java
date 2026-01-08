package com.teste.testegfx.data.remote;

import com.teste.testegfx.data.model.Post;

import java.util.List;

import retrofit2.Response;
import com.teste.testegfx.data.common.Callback;


import java.util.concurrent.Executor;


public class PostRemoteDataSource implements PostDataSource {

    private final PostApi api;
    private final Executor executor;

    public PostRemoteDataSource(PostApi api, Executor executor) {
        this.api = api;
        this.executor = executor;
    }

    @Override
    public void fetchPosts(Callback<List<Post>> callback) {
        executor.execute(() -> {
            try {
                Response<List<Post>> response = api.getPosts().execute();

                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(
                            new Exception("Erro na resposta da API")
                    );
                }
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }

}

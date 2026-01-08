package com.teste.testegfx.ui;

import com.teste.testegfx.data.remote.ApiClient;
import com.teste.testegfx.data.remote.PostApi;
import com.teste.testegfx.data.remote.PostDataSource;
import com.teste.testegfx.data.remote.PostRemoteDataSource;
import com.teste.testegfx.data.repository.PostRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppModule {

    private static Executor executor;

    private static Executor provideExecutor() {
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        return executor;
    }

    public static PostApi providePostApi() {
        return ApiClient.create(PostApi.class);
    }

    public static PostDataSource providePostRemoteDataSource() {
        return new PostRemoteDataSource(
                providePostApi(),
                provideExecutor()
        );
    }

    public static PostRepository providePostRepository() {
        return new PostRepository(
                providePostRemoteDataSource()
        );
    }
}

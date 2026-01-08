package com.teste.testegfx.ui;

import com.teste.testegfx.data.remote.ApiClient;
import com.teste.testegfx.data.remote.PostApi;
import com.teste.testegfx.data.remote.PostDataSource;
import com.teste.testegfx.data.remote.PostRemoteDataSource;
import com.teste.testegfx.data.repository.PostRepository;

public final class AppModule {

    private AppModule() {
    }

    public static PostRepository providePostRepository() {
        PostApi api = ApiClient.create(PostApi.class);
        PostDataSource remote =
                new PostRemoteDataSource(api);

        return new PostRepository(remote);
    }
}

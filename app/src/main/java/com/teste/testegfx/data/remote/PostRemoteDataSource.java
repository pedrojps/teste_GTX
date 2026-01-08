package com.teste.testegfx.data.remote;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.teste.testegfx.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRemoteDataSource implements PostDataSource {

    private final PostApi api;

    public PostRemoteDataSource(PostApi api) {
        this.api = api;
    }

    @Override
    public LiveData<List<Post>> getPosts() {
        MutableLiveData<List<Post>> liveData = new MutableLiveData<>();

        api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(
                    Call<List<Post>> call,
                    Response<List<Post>> response
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                liveData.setValue(null);
            }
        });

        return liveData;
    }
}

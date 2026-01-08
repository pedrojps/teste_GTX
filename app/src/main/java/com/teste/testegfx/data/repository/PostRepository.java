package com.teste.testegfx.data.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.teste.testegfx.data.common.Callback;
import com.teste.testegfx.data.model.Post;
import com.teste.testegfx.data.remote.PostDataSource;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private final PostDataSource remoteDataSource;

    public PostRepository(PostDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public LiveData<List<Post>> getPostsFiltered(boolean filterEnabled) {
        MutableLiveData<List<Post>> liveData = new MutableLiveData<>();

        remoteDataSource.fetchPosts(new Callback<List<Post>>() {
            @Override
            public void onSuccess(List<Post> data) {
                if (filterEnabled) {
                    liveData.postValue(filterEvenIds(data));
                } else {
                    liveData.postValue(data);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                liveData.postValue(new ArrayList<>());
            }
        });

        return liveData;
    }


    public List<Post> filterEvenIds(List<Post> posts) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getId() % 2 == 0) {
                result.add(post);
            }
        }
        return result;
    }

}

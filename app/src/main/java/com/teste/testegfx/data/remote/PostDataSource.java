package com.teste.testegfx.data.remote;

import androidx.lifecycle.LiveData;

import com.teste.testegfx.data.common.Callback;
import com.teste.testegfx.data.model.Post;

import java.util.List;

public interface PostDataSource {
    void fetchPosts(Callback<List<Post>> callback);
}

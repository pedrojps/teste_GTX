package com.teste.testegfx.data.repository;
import androidx.lifecycle.LiveData;

import com.teste.testegfx.data.model.Post;
import com.teste.testegfx.data.remote.PostDataSource;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private final PostDataSource dataSource;

    public PostRepository(PostDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public LiveData<List<Post>> getPosts() {
        return dataSource.getPosts();
    }

    public List<Post> filterEvenId(List<Post> posts) {
        List<Post> filtered = new ArrayList<>();

        if (posts == null) return filtered;

        for (Post post : posts) {
            if (post.getId() % 2 == 0) {
                filtered.add(post);
            }
        }
        return filtered;
    }
}

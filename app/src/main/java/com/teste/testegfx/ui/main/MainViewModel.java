package com.teste.testegfx.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.teste.testegfx.data.model.Post;
import com.teste.testegfx.data.repository.PostRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final PostRepository repository;
    private final MediatorLiveData<List<Post>> posts =
            new MediatorLiveData<>();

    private List<Post> originalPosts;

    public MainViewModel(PostRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public void loadPosts() {
        posts.addSource(repository.getPosts(), result -> {
            originalPosts = result;
            posts.setValue(result);
        });
    }

    public void onFilterChanged(boolean onlyEvenIds) {
        if (originalPosts == null) return;

        posts.setValue(
                onlyEvenIds
                        ? repository.filterEvenId(originalPosts)
                        : originalPosts
        );
    }
}


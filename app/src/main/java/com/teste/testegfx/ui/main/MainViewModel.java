package com.teste.testegfx.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.teste.testegfx.data.model.Post;
import com.teste.testegfx.data.repository.PostRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final PostRepository repository;

    private final MutableLiveData<Boolean> filterEnabled =
            new MutableLiveData<>(false);

    private LiveData<List<Post>> posts;

    public MainViewModel(PostRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Post>> getPosts() {
        if (posts == null) {
            posts = Transformations.switchMap(
                    filterEnabled,
                    enabled -> repository.getPostsFiltered(enabled)
            );
        }
        return posts;
    }

    public void onFilterChanged(boolean enabled) {
        filterEnabled.setValue(enabled);
    }
}


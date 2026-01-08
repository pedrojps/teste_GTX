package com.teste.testegfx.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.teste.testegfx.data.repository.PostRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final PostRepository repository;

    public MainViewModelFactory(PostRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        }

        throw new IllegalArgumentException(
                "Unknown ViewModel class"
        );
    }
}

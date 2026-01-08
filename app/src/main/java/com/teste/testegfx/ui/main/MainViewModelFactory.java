package com.teste.testegfx.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.teste.testegfx.data.repository.PostRepository;
import com.teste.testegfx.ui.AppModule;


public class MainViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        PostRepository repository =
                AppModule.providePostRepository();

        return (T) new MainViewModel(repository);
    }
}
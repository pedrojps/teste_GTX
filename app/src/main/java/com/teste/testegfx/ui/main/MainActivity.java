package com.teste.testegfx.ui.main;
import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teste.testegfx.R;
import com.teste.testegfx.ui.AppModule;
import com.teste.testegfx.ui.main.Adapter.PostAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private PostAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupViewModel();
        setupObservers();
        setupFilter();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupViewModel() {
        MainViewModelFactory factory =
                new MainViewModelFactory(
                        AppModule.providePostRepository()
                );

        viewModel = new ViewModelProvider(this, factory)
                .get(MainViewModel.class);

        viewModel.loadPosts();
    }

    private void setupObservers() {
        viewModel.getPosts().observe(this, posts -> {
            adapter.submitList(posts);
        });
    }

    private void setupFilter() {
        Switch filterSwitch = findViewById(R.id.switchFilter);

        filterSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) ->
                        viewModel.onFilterChanged(isChecked)
        );
    }
}

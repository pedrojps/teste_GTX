package com.teste.testegfx.data.remote;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.teste.testegfx.data.model.Post;

public class PostRemoteDataSourceTest {

    @Rule
    public InstantTaskExecutorRule rule =
            new InstantTaskExecutorRule();

    private PostApi api;
    private PostRemoteDataSource dataSource;

    @Before
    public void setup() {
        api = mock(PostApi.class);
        dataSource = new PostRemoteDataSource(api);
    }

    @Test
    public void getPosts_shouldEmitPostsFromApi() {
        // Arrange
        Call<List<Post>> call = mock(Call.class);
        when(api.getPosts()).thenReturn(call);

        Post post1 = new Post(0,1, "Title 1", "Body 1");
        Post post2 = new Post(0,2, "Title 2", "Body 2");

        List<Post> mockResponse =
                Arrays.asList(post1, post2);

        ArgumentCaptor<Callback<List<Post>>> captor =
                ArgumentCaptor.forClass(Callback.class);

        // Act
        LiveData<List<Post>> liveData =
                dataSource.getPosts();

        verify(call).enqueue(captor.capture());

        captor.getValue().onResponse(
                call,
                Response.success(mockResponse)
        );

        // Assert
        List<Post> result = liveData.getValue();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }
}

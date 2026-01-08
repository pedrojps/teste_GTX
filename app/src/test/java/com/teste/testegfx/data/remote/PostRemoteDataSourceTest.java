package com.teste.testegfx.data.remote;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.teste.testegfx.data.common.Callback;
import com.teste.testegfx.data.model.Post;

public class PostRemoteDataSourceTest {

    private MockWebServer mockWebServer;
    private PostRemoteDataSource dataSource;

    @Before
    public void setup() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApi api = retrofit.create(PostApi.class);

        Executor executor = new ImmediateExecutor();

        dataSource = new PostRemoteDataSource(api, executor);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void fetchPosts_shouldReturnParsedPosts() throws Exception {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(loadJson("posts_success.json"))
        );

        dataSource.fetchPosts(new Callback<List<Post>>() {
            @Override
            public void onSuccess(List<Post> data) {
                assertNotNull(data);
                assertEquals(2, data.size());
                assertEquals(1, data.get(0).getId());
            }

            @Override
            public void onError(Throwable throwable) {
                fail("Não deveria cair no erro");
            }
        });
    }


    public class ImmediateExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run(); // executa na mesma thread
        }
    }

    private String loadJson(String fileName) throws Exception {
        InputStream inputStream =
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalStateException(
                    "Arquivo JSON não encontrado: " + fileName
            );
        }

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, StandardCharsets.UTF_8);
    }

}

package com.teste.testegfx.data.remote;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teste.testegfx.data.model.Post;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiTest {

    private MockWebServer mockWebServer;
    private PostApi api;

    @Before
    public void setup() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(PostApi.class);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void getPosts_shouldParseJsonCorrectly() throws Exception {
        // Arrange
        String json = loadJson("posts_success.json");

        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(json)
        );

        // Act
        Response<List<Post>> response =
                api.getPosts().execute();

        // Assert
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());

        List<Post> posts = response.body();

        assertEquals(2, posts.size());
        assertEquals(1, posts.get(0).getId());
        assertEquals("Post de teste", posts.get(0).getTitle());
    }

    private String loadJson(String fileName) throws Exception {
        InputStream inputStream =
                getClass().getClassLoader()
                        .getResourceAsStream(fileName);

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        return new String(buffer, StandardCharsets.UTF_8);
    }
}

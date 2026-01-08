package com.teste.testegfx.data.repository;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

import com.teste.testegfx.data.model.Post;

public class PostRepositoryTest {

    private PostRepository repository;

    @Before
    public void setup() {
        // DataSource não será usado no teste
        repository = new PostRepository(null);
    }

    @Test
    public void filterEvenId_shouldReturnOnlyEvenIds() {
        // Arrange
        Post post1 = new Post(0,1, "Title 1", "Body 1");
        Post post2 = new Post(0,2, "Title 2", "Body 2");
        Post post3 = new Post(0,3, "Title 3", "Body 3");
        Post post4 = new Post(0,4, "Title 4", "Body 4");

        List<Post> posts =
                Arrays.asList(post1, post2, post3, post4);

        // Act
        List<Post> result =
                repository.filterEvenIds(posts);

        // Assert
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getId());
        assertEquals(4, result.get(1).getId());
    }
}

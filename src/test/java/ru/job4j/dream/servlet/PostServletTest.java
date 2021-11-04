package ru.job4j.dream.servlet;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {
    @Before
    public void clearTables() {
        PsqlStore.instOf().clear();
    }

    @Test
    public void whenCreatePost() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("New post");

        new PostServlet().doPost(req, resp);

        Post post = PsqlStore.instOf().findAllPosts().iterator().next();
        assertThat(post, notNullValue());
        assertThat(post.getName(), is("New post"));
    }

    @Test
    public void whenUpdatePost() throws IOException {
        Post post = new Post(0, "New post");
        PsqlStore.instOf().save(post);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(post.getId()));
        when(req.getParameter("name")).thenReturn("Updated post");

        new PostServlet().doPost(req, resp);

        Post rsl = PsqlStore.instOf().findAllPosts().iterator().next();
        assertThat(rsl.getName(), is("Updated post"));
    }
}
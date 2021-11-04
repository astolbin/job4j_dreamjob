package ru.job4j.dream.servlet;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
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

public class CandidateServletTest {
    @Before
    public void clearTables() {
        PsqlStore.instOf().clear();
    }

    @Test
    public void whenCreatePost() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("New candidate");

        new CandidateServlet().doPost(req, resp);

        Candidate candidate = PsqlStore.instOf().findAllCandidates().iterator().next();
        assertThat(candidate, notNullValue());
        assertThat(candidate.getName(), is("New candidate"));
    }

    @Test
    public void whenUpdatePost() throws IOException {
        Candidate candidate = new Candidate(0, "New candidate");
        PsqlStore.instOf().save(candidate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(candidate.getId()));
        when(req.getParameter("name")).thenReturn("Updated candidate");

        new CandidateServlet().doPost(req, resp);

        Candidate rsl = PsqlStore.instOf().findAllCandidates().iterator().next();
        assertThat(rsl.getName(), is("Updated candidate"));
    }
}
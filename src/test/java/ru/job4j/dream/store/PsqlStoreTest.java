package ru.job4j.dream.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PsqlStoreTest {
    @Before
    public void clearTables() {
        PsqlStore.instOf().clear();
    }

    @Test
    public void whenCreatePost() {
        Store store = PsqlStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = PsqlStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        post.setName("Java Job 2");
        store.save(post);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is("Java Job 2"));
    }

    @Test
    public void whenGetAllPosts() {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job 1"));
        store.save(new Post(0, "Java Job 2"));
        Collection<Post> postsInDb = store.findAllPosts();
        assertThat(postsInDb.size(), is(2));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = PsqlStore.instOf();
        Candidate candidate = new Candidate(0, "Candidate");
        store.save(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenDeleteCandidate() {
        Store store = PsqlStore.instOf();
        Candidate candidate = new Candidate(0, "Candidate");
        store.save(candidate);
        assertThat(store.findCandidateById(candidate.getId()), is(candidate));
        store.deleteCandidate(candidate.getId());
        assertThat(store.findAllCandidates().size(), is(0));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = PsqlStore.instOf();
        Candidate candidate = new Candidate(0, "Candidate");
        store.save(candidate);
        candidate.setName("Candidate 2");
        store.save(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is("Candidate 2"));
    }

    @Test
    public void whenGetAllCandidates() {
        Store store = PsqlStore.instOf();
        store.save(new Candidate(0, "Candidate 1"));
        store.save(new Candidate(0, "Candidate 2"));
        Collection<Candidate> candidateInDb = store.findAllCandidates();
        assertThat(candidateInDb.size(), is(2));
    }

    @Test
    public void whenCreateUser() {
        Store store = PsqlStore.instOf();
        User user = new User(0, "User", "test@mail.com", "password");
        store.save(user);
        User userInDb = store.findUserByEmail(user.getEmail());
        assertThat(userInDb.getName(), is(user.getName()));
    }

    @Test
    public void whenUpdateUser() {
        Store store = PsqlStore.instOf();
        User user = new User(0, "User", "test@mail.com", "password");
        store.save(user);
        user.setName("User 2");
        store.save(user);
        User userInDb = store.findUserByEmail(user.getEmail());
        assertThat(userInDb.getName(), is("User 2"));
    }
}
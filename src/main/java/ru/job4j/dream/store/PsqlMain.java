package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        store.save(new Candidate(0, "Java candidate"));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }

        Post post = store.findPostById(1);
        post.setName("Java Job 2");
        store.save(post);
        Post updatedPost = store.findPostById(1);
        System.out.println(updatedPost.getId() + " " + updatedPost.getName());

        Candidate candidate = store.findCandidateById(1);
        candidate.setName("Java candidate 2");
        store.save(candidate);
        Candidate updatedCandidate = store.findCandidateById(1);
        System.out.println(updatedCandidate.getId() + " " + updatedCandidate.getName());
    }
}

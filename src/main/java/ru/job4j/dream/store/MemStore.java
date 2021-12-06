package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private static final MemStore INST = new MemStore();
    private static final AtomicInteger POST_ID = new AtomicInteger(4);
    private static final AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private static final AtomicInteger USER_ID = new AtomicInteger(4);
    private static final AtomicInteger CITY_ID = new AtomicInteger(4);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final Map<Integer, City> cities = new ConcurrentHashMap<>();

    private MemStore() {

    }

    public static MemStore instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public Collection<City> findAllCities() {
        return cities.values();
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    public void deleteCandidate(int id) {
        candidates.remove(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    @Override
    public void save(City city) {
        if (city.getId() == 0) {
            city.setId(CITY_ID.incrementAndGet());
        }
        cities.put(city.getId(), city);
    }

    @Override
    public City findCityById(int id) {
        return cities.get(id);
    }

    @Override
    public Collection<Post> findTodayPosts() {
        return posts.values().stream()
                .filter(post -> post.getCreated().isAfter(LocalDateTime.now().minusHours(24)))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Candidate> findTodayCandidates() {
        return candidates.values().stream()
                .filter(candidate -> {
                    return candidate.getCreated().isAfter(LocalDateTime.now().minusHours(24));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        posts.clear();
        candidates.clear();
        users.clear();
        cities.clear();
    }
}
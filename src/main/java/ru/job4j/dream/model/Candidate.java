package ru.job4j.dream.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private int cityId;
    private LocalDateTime created;

    public Candidate(int id, String name, int cityId, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.created = created;
    }

    public Candidate(int id, String name, int cityId) {
        this(id, name, cityId, LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && Objects.equals(name, candidate.name)
                && Objects.equals(cityId, candidate.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cityId);
    }
}

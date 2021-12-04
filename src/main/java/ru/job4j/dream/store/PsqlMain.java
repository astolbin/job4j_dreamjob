package ru.job4j.dream.store;

import ru.job4j.dream.model.City;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new City(0, "New York"));
        store.save(new City(0, "London"));
        store.save(new City(0, "Moscow"));
    }
}

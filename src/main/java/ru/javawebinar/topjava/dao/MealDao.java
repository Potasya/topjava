package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Marisha on 11/12/16.
 */
public interface MealDao {
    void create(Meal meal);

    void update(Meal meal);

    void delete(Long id);

    List<Meal> readAll();

    Meal getById(Long id);
}

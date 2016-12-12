package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Marisha on 11/12/16.
 */
public class MealDaoImpl implements MealDao{

    private static List<Meal> meals = new CopyOnWriteArrayList<>(Arrays.asList(
            new Meal(100L, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
            new Meal(101L, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
            new Meal(102L, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
            new Meal(103L, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
            new Meal(104L, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
            new Meal(105L, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510)));

    private static AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Meal getMealById(Long id){
        for (Meal m: meals)
            if (m.getId().equals(id))
                return m;
        return null;
    }

    @Override
    public Long create(Meal meal) {
        meal.setId(idGenerator.getAndIncrement());
        meals.add(meal);
        return meal.getId();
    }

    @Override
    public void delete(Long id) {
        meals.remove(getMealById(id));
    }

    @Override
    public List<Meal> readAll() {
        return meals;
    }

    @Override
    public void update(Meal meal) {
        delete(meal.getId());
        create(meal);
    }
}

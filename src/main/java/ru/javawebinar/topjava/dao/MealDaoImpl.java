package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by Marisha on 11/12/16.
 */
public class MealDaoImpl implements MealDao {

    private static Map<Long, Meal> meals = new ConcurrentHashMap<>();

    static {
        meals.put(100L, new Meal(100L, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500));
        meals.put(101L, new Meal(101L, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000));
        meals.put(102L, new Meal(102L, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500));
        meals.put(103L, new Meal(103L, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000));
        meals.put(104L, new Meal(104L, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500));
        meals.put(105L, new Meal(105L, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510));
    }

    private static AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Meal getById(Long id) {
        return meals.get(id);
    }

    @Override
    public void create(Meal meal) {
        meal.setId(idGenerator.getAndIncrement());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(Long id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> readAll() {
        return meals.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }
}

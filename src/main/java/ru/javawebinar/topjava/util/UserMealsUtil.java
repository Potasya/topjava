package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();

/*  Test  */
//        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        for (UserMealWithExceed e: list) {
//            System.out.println(e.getDateTime() + " " + e.getDescription() + " " + e.getCalories() + " " + e.isExceed());
//        }
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


/* Java 8 Stream API */
        Map<LocalDate, Integer> m = mealList.stream()
                .collect(Collectors.groupingBy((UserMeal meal) -> meal.getDateTime().toLocalDate(),
                        Collectors.reducing(0, UserMeal::getCalories, Integer::sum)));

        return mealList.stream()
                .filter(e -> TimeUtil.isBetween(e.getDateTime().toLocalTime(), startTime, endTime))
                .map(e -> {
            LocalDate ld = e.getDateTime().toLocalDate();
            return new UserMealWithExceed(e.getDateTime(), e.getDescription(), e.getCalories(), m.get(ld) > caloriesPerDay);
        }).collect(Collectors.toList());

/* Java 7 API */
//      Map<LocalDate,Integer> mapOfCaliories = new HashMap<>();
//        for (UserMeal meal: mealList) {
//            LocalDate date = meal.getDateTime().toLocalDate();
//            if (!mapOfCaliories.containsKey(date)) {
//                mapOfCaliories.put(date, meal.getCalories());
//            }
//            else {
//                mapOfCaliories.put(date, mapOfCaliories.get(date) + meal.getCalories());
//            }
//        }
//        for (UserMeal meal: mealList) {
//            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(),startTime,endTime)) {
//                userMealWithExceedList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),mapOfCaliories.get(meal.getDateTime().toLocalDate())>caloriesPerDay));
//            }
//        }

    }
}

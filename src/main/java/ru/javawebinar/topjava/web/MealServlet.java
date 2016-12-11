package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Marisha on 11/12/16.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(MealServlet.class);

    List<Meal> meals;
    List<MealWithExceed> mealsWithExceeded;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510)
        );
         mealsWithExceeded = MealsUtil.getFilteredWithExceeded(meals, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        req.setAttribute("meals", mealsWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req,resp);
    }
}

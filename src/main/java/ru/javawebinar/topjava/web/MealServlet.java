package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Marisha on 11/12/16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MealDao dao = new MealDaoImpl();

    private static final String MEAL_LIST = "/meals.jsp";
    private static final String UPDATE_OR_CREATE = "/meal.jsp";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm";

    private List<MealWithExceed> getListWithExceed() {
        return MealsUtil.getFilteredWithExceeded(dao.readAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            req.setAttribute("meals", getListWithExceed());
            req.getRequestDispatcher(MEAL_LIST).forward(req, resp);
        }

        String forward = "";
        switch (action.toLowerCase()) {
            case "create":
                forward = UPDATE_OR_CREATE;
                break;
            case "delete":
                dao.delete(Long.parseLong(req.getParameter("id")));
                forward = MEAL_LIST;
                req.setAttribute("meals", getListWithExceed());
                break;
            case "update":
                forward = UPDATE_OR_CREATE;
                Meal meal = dao.getById(Long.parseLong(req.getParameter("id")));
                req.setAttribute("meal", meal);
                break;
            default:
                req.setAttribute("meals", getListWithExceed());
                forward = MEAL_LIST;
        }

        req.getRequestDispatcher(forward).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(req.getParameter("id") == null || req.getParameter("id").isEmpty() ? "-1" : req.getParameter("id"));
        LocalDateTime time = LocalDateTime.parse(req.getParameter("time"), DateTimeFormatter.ofPattern(dateTimeFormat));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Meal meal = new Meal(id, time, description, calories);
        if (meal.getId().equals(-1L)) {
            dao.create(meal);
        } else {
            dao.update(meal);
        }
        req.setAttribute("meals", getListWithExceed());
        req.getRequestDispatcher(MEAL_LIST).forward(req, resp);
    }
}

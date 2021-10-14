package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        if (isIncompleteData(req)) {
            req.setAttribute("error", "Не заполнены все поля формы.");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }

        if (hasUser(req.getParameter("email"))) {
            req.setAttribute("error", "Пользователь с таким email-адресом уже существует.");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        PsqlStore.instOf().save(new User(0, name, email, password));
        resp.sendRedirect(req.getContextPath() + "/auth.do");
    }

    private boolean hasUser(String email) {
        return PsqlStore.instOf().findUserByEmail(email) != null;
    }

    private boolean isIncompleteData(HttpServletRequest req) {
        return req.getParameter("name").isEmpty()
                || req.getParameter("name").isEmpty()
                || req.getParameter("name").isEmpty();
    }
}

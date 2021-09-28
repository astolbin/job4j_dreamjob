package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Post post = new Post(0, "");
        if (id != null) {
            post = MemStore.instOf().findPostById(Integer.parseInt(id));
        }
        req.setAttribute("post", post);
        req.getRequestDispatcher("/post/edit.jsp").forward(req, resp);
    }
}

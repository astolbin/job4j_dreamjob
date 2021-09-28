package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Candidate candidate = new Candidate(0, "");
        if (id != null) {
            candidate = MemStore.instOf().findCandidateById(Integer.parseInt(id));
        }
        req.setAttribute("candidate", candidate);
        req.getRequestDispatcher("/candidate/edit.jsp").forward(req, resp);
    }
}

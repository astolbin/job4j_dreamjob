package ru.job4j.dream.servlet;

import ru.job4j.dream.store.PsqlStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int candidateId = Integer.parseInt(req.getParameter("id"));
        PsqlStore.instOf().deleteCandidate(candidateId);

        for (File file : new File("c:\\images\\").listFiles()) {
            String fileName = file.getName().replaceFirst("[.][^.]+$", "");
            if (fileName.equals(req.getParameter("id"))) {
                file.delete();
                break;
            }
        }

        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}

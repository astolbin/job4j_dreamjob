package ru.job4j.dream.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File rsl = null;
        for (File file : new File("c:\\images\\").listFiles()) {
            String fileName = file.getName().replaceFirst("[.][^.]+$", "");
            if (req.getParameter("id").equals(fileName)) {
                rsl = file;
                break;
            }
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + rsl.getName() + "\"");

        try (FileInputStream stream = new FileInputStream(rsl)){
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}

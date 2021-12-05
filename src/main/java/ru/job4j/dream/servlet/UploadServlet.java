package ru.job4j.dream.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UploadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("id", req.getParameter("id"));
        req.getRequestDispatcher("/candidate/PhotoUpload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:\\images\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String candidateId = req.getParameter("id");
                    String fileExtension = item.getName().substring(
                            item.getName().lastIndexOf(".")
                    );
                    File file = new File(folder + File.separator + candidateId + fileExtension);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            LOG.error("Exception", e);
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}

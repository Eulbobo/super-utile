package superbad.idea.servlet;

import superbad.idea.image.Julien;

import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JulienDitServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        Julien j = new Julien(req.getParameter("m"));
        OutputStream out = resp.getOutputStream();
        ImageIO.write(j.writeImage(), "png", out);
        out.close();
    }

}

package mk.finki.ukim.mk.lab.web.servlet;

import org.springframework.context.annotation.Profile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("servlet")
@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        System.out.println("[WP-Log] {Logout}");
        resp.sendRedirect(" ");
    }
}

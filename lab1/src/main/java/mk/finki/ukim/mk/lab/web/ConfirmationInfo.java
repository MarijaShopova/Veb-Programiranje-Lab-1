package mk.finki.ukim.mk.lab.web;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {

    private final TemplateEngine templateEngine;

    public ConfirmationInfo(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("pizzaType", req.getSession().getAttribute("pizzaType"));
        context.setVariable("pizzaSize", req.getSession().getAttribute("pizzaSize"));
        context.setVariable("clientName", req.getParameter("clientName"));
        context.setVariable("clientAddress", req.getParameter("clientAddress"));
        context.setVariable("ipAddress", req.getRemoteHost());
        context.setVariable("browser", req.getHeader("User-Agent"));
        resp.setContentType("text/html; charset=UTF-8");
        System.out.println("[WP-Log] {doPost ConfirmationInfo}");
        this.templateEngine.process("confirmationInfo", context, resp.getWriter());
    }
}

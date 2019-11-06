package mk.finki.ukim.mk.lab.web;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/PizzaOrder.do", name = "pizzaOrder")
public class PizzaOrder extends HttpServlet {
    private final TemplateEngine templateEngine;

    public PizzaOrder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pizzaSize = req.getParameter("pizza_size");
        req.getSession().setAttribute("pizzaSize", pizzaSize);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaSize", pizzaSize);
        webContext.setVariable("pizzaType", req.getSession().getAttribute("pizzaType"));
        resp.setContentType("text/html; charset=UTF-8");
        System.out.println("[WP-Log] {doGet PizzaOrder}");
        this.templateEngine.process("deliveryInfo", webContext, resp.getWriter());
    }
}

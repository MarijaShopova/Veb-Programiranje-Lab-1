package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "", name = "showPizza")
public class ShowPizza extends HttpServlet {

    private final PizzaService service;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowPizza(PizzaService service, SpringTemplateEngine springTemplateEngine) {
        this.service = service;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Pizza> pizzas = this.service.listPizzas();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzas", pizzas);
        resp.setContentType("text/html; charset=UTF-8");
        System.out.println("[WP-Log] {doGet ShowPizzas}");
        this.springTemplateEngine.process("listPizzas", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pizzaType = req.getParameter("pizza");
        HttpSession session = req.getSession();
        session.setAttribute("pizzaType", pizzaType);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaType", pizzaType);
        resp.setContentType("text/html; charset=UTF-8");
        System.out.println("[WP-Log] {doPost ShowPizza}");
        this.springTemplateEngine.process("selectPizzaSize", webContext, resp.getWriter());
    }
}

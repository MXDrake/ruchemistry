package main.controller;

import main.model.order.Item;
import main.model.order.ShopCart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ShopController {

    static final ShopCart shopCart = new ShopCart();

    @RequestMapping(value = {"/shop"}, method = RequestMethod.GET)
    public void shop() throws IOException, ServletException, MessagingException {
        ModelAndView model = new ModelAndView("shop");

    }

    @RequestMapping(value = {"/shop"}, method = RequestMethod.POST)
    public void shopPost(HttpServletRequest request) throws IOException, ServletException, MessagingException {
        ModelAndView model = new ModelAndView("shop");
        Item item = new Item(request.getParameter("item"), request.getParameter("type"),
                Integer.parseInt(request.getParameter("count")), Double.parseDouble(request.getParameter("price")));

        shopCart.addItem(item);

        request.setAttribute("shopcart", shopCart);


    }
}

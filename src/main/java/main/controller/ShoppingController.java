package main.controller;

import main.Helper;
import main.model.Cart;
import main.model.Reagent;
import main.model.User;
import main.service.PageService;
import main.service.ReagentService;
import main.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller
public class ShoppingController {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingController.class);

	private UserService userService;
	private ReagentService reagentService;
	private PageService pageService;

	@Autowired
	public ShoppingController(UserService userService, ReagentService reagentService, PageService pageService){
		this.userService = userService;
		this.reagentService = reagentService;
		this.pageService = pageService;
	}

	@RequestMapping(value = {"/user/reagent/buy"}, method = RequestMethod.POST)
	public String addToCart(Long reagentId, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");

		if ( cart == null){
			  cart = new Cart();
		 }

		Reagent reagent = reagentService.get(reagentId);
		cart.setBasket(reagent, 1);
		session.setAttribute("cart", cart);
		return "reagent :: cart";
	}

	@RequestMapping(value = {"/user/cart"}, method = RequestMethod.GET)
	public ModelAndView cart() {
		ModelAndView model = new ModelAndView("cart");
		try {

			model = Helper.getMenu(model, "cart");
			if (model == null) {
				return new ModelAndView("redirect: /");
			}
			model.addObject("page", pageService.getByName("cart"));
			return model;
		} catch (Exception e) {
			logger.error("while opened page = cart");
			return new ModelAndView("redirect: /");
		}
	}

}

package main.controller;

import main.Helper;
import main.model.*;
import main.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingController {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingController.class);

	private UserService userService;
	private ReagentService reagentService;
	private PageService pageService;
	private GoodsService goodsService;
	private OrderService orderService;

	@Autowired
	public ShoppingController(UserService userService, ReagentService reagentService, PageService pageService,
							  GoodsService goodsService, OrderService orderService) {
		this.userService = userService;
		this.reagentService = reagentService;
		this.pageService = pageService;
		this.goodsService = goodsService;
		this.orderService = orderService;
	}

	@RequestMapping(value = {"/user/reagent/buy"}, method = RequestMethod.POST)
	public String addToCart(Long goodsId, Integer count, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
		}

		//Reagent reagent = reagentService.get(reagentId);
		Goods goods = goodsService.get(goodsId);
		if (cart.getBasket().containsKey(goods)) {
			count += cart.getBasket().get(goods);
		}
		cart.setBasket(goods, count);
		session.setAttribute("cart", cart);
		return "reagent :: cart";
	}

	@RequestMapping(value = {"/user/cart"}, method = RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("cart");
		try {
			//создаем меню и описание страницы
			model = Helper.getMenu(model, "cart");
			if (model == null) {
				return new ModelAndView("redirect: /");
			}
			//link for back url
			String backUrl = request.getHeader("Referer");
			model.addObject("backurl", backUrl);

			model.addObject("page", pageService.getByName("cart"));
			return model;
		} catch (Exception e) {
			logger.error("while opened page = cart");
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping(value = {"/user/cart/position/update"}, method = RequestMethod.POST)
	public ModelAndView cartUpdatePosition(Long positionId, Integer count, HttpSession session) {
		try {
			//Меняем количество в позиции корзины
			Goods goods = goodsService.get(positionId);
			Cart cart = (Cart) session.getAttribute("cart");
			cart.addPosition(goods, count);
			session.setAttribute("cart", cart);
		} catch (Exception e) {
			logger.error("while updating cart count");
		}
		return new ModelAndView("redirect:/user/cart");
	}

	@RequestMapping(value = {"/user/cart/position/delete/{goodsId}"}, method = RequestMethod.GET)
	public ModelAndView cartDeletePosition(@PathVariable Long goodsId, HttpSession session) {
		try {
			//Удаляем позицию из корзины
			Goods goods = goodsService.get(goodsId);
			Cart cart = (Cart) session.getAttribute("cart");
			cart.deletePosition(goods);
			session.setAttribute("cart", cart);
		} catch (Exception e) {
			logger.error("while deleted cart count");
		}
		return new ModelAndView("redirect:/user/cart");
	}

	@RequestMapping(value = "/user/order/create", method = RequestMethod.POST)
	public ModelAndView createOrder(HttpSession session){
		ModelAndView model = new ModelAndView("profile");
		User user = userService.getCurrentUser();
		Cart cart = (Cart) session.getAttribute("cart");
		orderService.createOrder(cart, user);
		session.setAttribute("cart", null);
		return model;

	}
}

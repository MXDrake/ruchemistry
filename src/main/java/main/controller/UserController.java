package main.controller;

import main.Helper;
import main.model.Goods;
import main.model.Reagent;
import main.model.User;
import main.service.PageService;
import main.service.ReagentService;
import main.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
//implements ErrorController
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final ReagentService reagentService;

	private final PageService pageService;

	private UserService userService;

	@Autowired
	public UserController(PageService pageService, ReagentService reagentService, UserService userService) {
		this.pageService = pageService;
		this.reagentService = reagentService;
		this.userService = userService;
	}

	@RequestMapping(value = {"/reagents"}, method = RequestMethod.GET)
	public ModelAndView mainGet(String search, String type, Integer pageNumber, String kind, HttpSession session) {
		ModelAndView model = new ModelAndView("reagents");
		try {
			//Reagents
			type = Helper.checkType(type, search);
			Page<Reagent> page = Helper.checkSearchParametrs(search, type, kind, pageNumber);
			if (search != null) {
				model.addObject("search", search);
			}
			model.addObject("reagentList", page);

			//Menu
			model = Helper.getMenu(model, "reagents");
			if (model == null) {
				return new ModelAndView("redirect: /");
			}

			//Pagination variables
			int current = page.getNumber() + 1;
			int totalPageCount = page.getTotalPages();
			int begin = Helper.getBeginPage(totalPageCount, current);
			int end = Helper.getEndPage(totalPageCount, begin);
			model.addObject("page", page);
			model.addObject("beginIndex", begin);
			model.addObject("endIndex", end);
			model.addObject("currentIndex", current);
			model.addObject("totalPageCount", totalPageCount);
			model.addObject("title", "Реактивы");
			model.addObject("type", type);
			return model;
		} catch (Exception e) {
			logger.error("while open /reagents");
			return new ModelAndView("redirect: /");
		}
	}

	@RequestMapping(value = {"/medications"}, method = RequestMethod.GET)
	public ModelAndView medications(String search, String type, Integer pageNumber, String kind, HttpSession session) {
		ModelAndView model = new ModelAndView("reagents");
		try {
			//Medicals
			if (kind == null) {
				kind = "%Medication%";
			}
			type = Helper.checkType(type, search);
			Page<Reagent> page = Helper.checkSearchParametrs(search, type, kind, pageNumber);
			if (search != null) {
				model.addObject("search", search);
			}
			model.addObject("reagentList", page);

			//Menu
			model = Helper.getMenu(model, "medications");
			if (model == null) {
				return new ModelAndView("redirect: /");
			}

			//Pagination variables
			int current = page.getNumber() + 1;
			int totalPageCount = page.getTotalPages();
			int begin = Helper.getBeginPage(totalPageCount, current);
			int end = Helper.getEndPage(totalPageCount, begin);

			model.addObject("page", page);
			model.addObject("beginIndex", begin);
			model.addObject("endIndex", end);
			model.addObject("currentIndex", current);
			model.addObject("totalPageCount", totalPageCount);
			model.addObject("title", "Лекарства");
			model.addObject("type", type);
			return model;
		} catch (Exception e) {
			logger.error("while opened medicaments ");
			return new ModelAndView("redirect: /");
		}
	}

	//Table Reagents and Medical
	@RequestMapping(value = {"/reagents/"}, method = RequestMethod.POST)
	public String getPage(int pageNumber, String search, String type, Model model, String kind, HttpSession session) {
		try {
			Page<Reagent> page;

			if (search != null & type != null) {
				if (pageNumber <= 0) {
					pageNumber = 1;
				}
				type = Helper.checkType(type, search);
				search = Helper.checkSearch(search);
				page = reagentService.search(search, type, kind, new PageRequest(pageNumber - 1, 50));
			} else {
				page = reagentService.getPage(kind, new PageRequest(pageNumber - 1, 50));
			}

			//Pagination variables
			int current = page.getNumber() + 1;
			int totalPageCount = page.getTotalPages();
			int begin = Helper.getBeginPage(totalPageCount, current);
			int end = Helper.getEndPage(totalPageCount, begin);

			model.addAttribute("page", page);
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", current);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("reagentList", page);

			//Session parametrs
			session.setAttribute("pageNumber", pageNumber);
			session.setAttribute("search", search);
			session.setAttribute("type", type);
			session.setAttribute("kind", kind.replaceAll("%", ""));

			return "reagents :: reagentTable";
		} catch (Exception e) {
			logger.error("while open /reagents");
			return null;
		}
	}

	//Static Pages
	@RequestMapping(value = {"/page/{name}"}, method = RequestMethod.GET)
	public ModelAndView pages(@PathVariable String name) {
		ModelAndView model = new ModelAndView("page");
		try {

			model = Helper.getMenu(model, name);
			if (model == null) {
				return new ModelAndView("redirect: /");
			}
			model.addObject("page", pageService.getByName(name));

			return model;
		} catch (Exception e) {
			logger.error("while opened page = {}", name);
			return new ModelAndView("redirect: /");
		}
	}

	//Reagent page
	@RequestMapping(value = {"/reagents/{reagentId}"}, method = RequestMethod.GET)
	public ModelAndView reagent(@PathVariable Long reagentId, HttpSession session) {
		ModelAndView model = new ModelAndView("reagent");
		try {
			model.addObject("menu", pageService.getMenu("main"));
			Reagent reagent = reagentService.get(reagentId);
			if (reagent != null) {
				model.addObject("reagent", reagent);
				List<Goods> fvv =reagent.getGoods();
				model.addObject("goods", reagent.getGoods());
			} else {
				return new ModelAndView("redirect: /reagents/");
			}

			//Session
			model.addObject("search", session.getAttribute("search"));
			model.addObject("type", session.getAttribute("type"));
			model.addObject("pageNumber", session.getAttribute("pageNumber"));
			model.addObject("kind", session.getAttribute("kind"));
			model = Helper.getTitle(model, reagent.getKind());

			return model;
		} catch (Exception e) {
			logger.error("while getting page id = {}", reagentId);
			return new ModelAndView("redirect: /reagents/");
		}
	}

	//Medication Page
	@RequestMapping(value = {"/medications/{medicId}"}, method = RequestMethod.GET)
	public ModelAndView medication(@PathVariable Long medicId) {
		ModelAndView model = new ModelAndView("reagent");
		try {
			model.addObject("menu", pageService.getMenu("main"));
			if (reagentService.get(medicId) != null) {
				model.addObject("reagent", reagentService.get(medicId));
			} else {
				return new ModelAndView("redirect: /medications");
			}
			return model;
		} catch (Exception e) {
			logger.error("while opened id = {}", medicId);
			return new ModelAndView("redirect: /medications");
		}
	}

	//Main Page
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView model = new ModelAndView("page");
		try {
			model.addObject("menu", pageService.getMenu("main"));
			model.addObject("page", pageService.getByName("main"));
			return model;
		} catch (Exception e) {
			logger.error("while opened home page");
			return null;
		}
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
							  @RequestParam(value = "logout", required = false) String logout,HttpServletRequest request) {
		try {
			ModelAndView model = new ModelAndView("page");

			User user = userService.getCurrentUser();
			if (user == null){
				model = Helper.getMenu(model, "login");
				model.addObject("page", pageService.getByName("login"));
			} else {
				model = new ModelAndView("profile");
				model.addObject("user", user);
				model = Helper.getMenu(model, "profile");
				return model;
			}

			if (error != null) {
				model.addObject("error", "Неверное имя или пароль");
			}

			if (logout != null) {
				model.addObject("msg", "Вы успешно вышли из системы.");
				logger.info("logged out successfully.");
			}
				//link for back url
				String backUrl = request.getHeader("Referer");
				model.addObject("backurl", backUrl);
			return model;
		} catch (Exception e) {
			logger.error("while singin");
			return new ModelAndView("redirect: /");
		}
	}

	//Sitemap generator
	@RequestMapping(value = {"/sitemap.xml"}, method = RequestMethod.GET)
	public ModelAndView sitemap() {
		ModelAndView model = new ModelAndView("sitemap");
		try {
			List<Reagent> a = reagentService.getAll("ChemicalAgent");

			model.addObject("reagents", reagentService.getAll("ChemicalAgent"));
			model.addObject("pages", pageService.getAll());
			model.addObject("medications", reagentService.getAll("Medication"));
			return model;
		} catch (Exception e) {
			logger.error("while getting sitemap");
			return new ModelAndView("redirect: /");
		}
	}

	// Pages for old links
	@RequestMapping(value = {"/chemical_agents"}, method = RequestMethod.GET)
	public ModelAndView chemical_agents(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("reagent");
		try {
			Long reagentId = Long.parseLong(request.getParameter("id"));
			request.setAttribute("menu", pageService.getMenu("main"));
			Reagent reagent = reagentService.get(reagentId);
			if (reagent != null) {
				model.addObject("reagent", reagent);
			} else {
				return new ModelAndView("redirect: /reagents/");
			}
			return model;
		} catch (Exception e) {
			logger.error("while getting chemical_agents ");
			return new ModelAndView("redirect: /reagents/");
		}
	}

	@RequestMapping("/user/profile")
	public ModelAndView profile(HttpServletRequest request, String action) {
		ModelAndView model = new ModelAndView("profile");
		model = Helper.getMenu(model, "profile");
		if (model == null) {
			return new ModelAndView("redirect: /");
		}
		if (action != null){
			model.addObject("edit", true);
		}
		User user = userService.getCurrentUser();
		model.addObject("user", user);
		model.addObject("page", pageService.getByName("profile"));

		//link for back url
		String backUrl = request.getHeader("Referer");
		model.addObject("backurl", backUrl);

		return model;
	}


	@RequestMapping("/user/registration")
	public ModelAndView registration(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("registration");
		model = Helper.getMenu(model, "registration");
		if (model == null) {
			return new ModelAndView("redirect: /");
		}
		User user = userService.getCurrentUser();
		model.addObject("user", user);
		model.addObject("page", pageService.getByName("registration"));

		//link for back url
		String backUrl = request.getHeader("Referer");
		model.addObject("backurl", backUrl);

		return model;
	}

//	@Override
//	public String getErrorPath() {
//		return "/error";
//	}
//
//	@RequestMapping("/error")
//	public ModelAndView error(HttpServletRequest request) {
//		logger.error("WhiteLabelError ");
//		return new ModelAndView("redirect:/");
//	}

	//	final Pars pars;
//
//	@RequestMapping(value = {"/pars"}, method = RequestMethod.GET)
//	public void pars() throws IOException, ServletException {
//		ModelAndView model = new ModelAndView("pars");
//		pars.pars();
//	}
}

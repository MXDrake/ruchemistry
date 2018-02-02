package main.controller;

import main.Helper;
import main.model.Reagent;
import main.service.PageService;
import main.service.Pars;
import main.service.ReagentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final ReagentService reagentService;

	private final PageService pageService;

	@Autowired
	public UserController(PageService pageService, ReagentService reagentService, Pars pars) {
		this.pageService = pageService;
		this.reagentService = reagentService;
	}

	@RequestMapping(value = {"/reagents"}, method = RequestMethod.GET)
	public ModelAndView mainGet(String search, String type, Integer pageNumber, String kind) {
		ModelAndView model = new ModelAndView("reagents");
		try {
			//Reagents
			Page<Reagent> page;
			if (search != null && type != null) {
				if (kind == null) {
					kind = "%%";
				} else {
					kind = "%" + kind + "%";
				}

				if (pageNumber == null || pageNumber <= 0) {
					pageNumber = 1;
				}
				page = reagentService.search(search, type, kind, new PageRequest(pageNumber - 1, 50));
				model.addObject("search", search);
			} else {
				kind = "%ChemicalAgent%";
				page = reagentService.getPage(kind, new PageRequest(0, 50));
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
			return model;
		} catch (Exception e) {
			logger.error("while open /reagents");
			return new ModelAndView("redirect: /");
		}
	}

	@RequestMapping(value = {"/medications"}, method = RequestMethod.GET)
	public ModelAndView medications() {
		ModelAndView model = new ModelAndView("reagents");
		try {
			String kind = "%Medication%";
			Page<Reagent> page = reagentService.getPage(kind, new PageRequest(0, 50));
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
			session.setAttribute("pageNumber", pageNumber);
			Page<Reagent> page;

			if (search != null & type != null) {
				if (pageNumber <= 0) {
					pageNumber = 1;
				}
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
			} else {
				return new ModelAndView("redirect: /reagents/");
			}

			//Session
			model.addObject("search", session.getAttribute("search"));
			model.addObject("type", session.getAttribute("type"));
			model.addObject("pageNumber", session.getAttribute("pageNumber"));
			model.addObject("kind", session.getAttribute("kind"));
			model = Helper.getTitle(model,reagent.getKind());

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
							  @RequestParam(value = "logout", required = false) String logout) {
		try {
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("login");
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

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request) {
		logger.error("WhiteLabelError ");
		return new ModelAndView("redirect:/");
	}

	//	final Pars pars;
//
//	@RequestMapping(value = {"/pars"}, method = RequestMethod.GET)
//	public void pars() throws IOException, ServletException {
//		ModelAndView model = new ModelAndView("pars");
//		pars.pars();
//	}
}

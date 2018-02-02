package main.controller;

import main.Helper;
import main.model.Pagination;
import main.model.Reagent;
import main.service.PageService;
import main.service.Pars;
import main.service.ReagentService;
import main.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ReagentService reagentService;

	@Autowired
	private Pagination paginationModel;

	@Autowired
	private PageService pageService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = {"/reagents"}, method = RequestMethod.GET)
	public ModelAndView mainGet(String search, String type, HttpServletRequest request, HttpSession session,
								HttpServletResponse response) throws IOException, ServletException {
		ModelAndView model = new ModelAndView("reagents");
		try {

			Page<Reagent> page;
			String kind;
			if (search != null && type != null) {
				kind = "%%";
				page = reagentService.search(search, type, kind, new PageRequest(0, 50));

			} else {
				kind = "%ChemicalAgent%";
				page = reagentService.getPage(kind, new PageRequest(0, 50));
			}

			paginationModel.setRows(page.getTotalElements());

			paginationModel.setPage(request.getParameter("page"));

			paginationModel.setNumber(request.getParameter("number"));

			if (paginationModel.getPage() > paginationModel.getPagesize()) {
				paginationModel.setPage(String.valueOf(paginationModel.getPagesize()));
			}
			request.setAttribute("reagentList", page);

			if (paginationModel.getPage() < 20) {
				paginationModel.setPage("20");
			}
			request.setAttribute("page", paginationModel);

			String pageName = "reagents";
			List list = pageService.getMenu(pageName);
			if (pageService.getByName(pageName) != null) {
				request.setAttribute("color", list.get(list.size() - 1));
				list.remove(list.size() - 1);
				request.setAttribute("menu", list);
			} else {
				request.getRequestDispatcher("/").forward(request, response);
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
			request.getRequestDispatcher("/").forward(request, response);
			logger.error("while open /reagents");
			return null;
		}

	}

	@RequestMapping(value = {"/reagents/"}, method = RequestMethod.POST)
	public String getPage(int pageNumber, String search, String type, Model model, String kind, HttpSession session)
			throws ServletException, IOException {
		try {

		session.setAttribute("pageNumber", pageNumber);
		Page<Reagent> page;

		if (pageNumber != 0) {
			PageRequest pageRequest = new PageRequest(pageNumber - 1, 50);
		} else {
			PageRequest pageRequest = new PageRequest(0, 50);
		}

		if (search != null & type != null) {
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

			return "reagents :: reagentTable";
		} catch (Exception e) {
			logger.error("while open /reagents");
			return null;
		}

	}

	@RequestMapping(value = {"/reagents/search"}, method = RequestMethod.POST)
	public String search(String search, String type, Model model, HttpServletRequest request, HttpSession session,
						 HttpServletResponse response) throws IOException, ServletException {

		try {
			if (type == null | search == null) {
				response.sendRedirect("/reagents");
				return null;
			}
			if (search.equals("") | type.equals("")) {
				response.sendRedirect("/reagents");
				return null;
			}

			List<Reagent> list = reagentService.searchBy(search, type);
			paginationModel.setRows((long) list.size());
			model.addAttribute("reagentList", list);
			model.addAttribute("page", paginationModel);
			model.addAttribute("menu", pageService.getMenu("main"));
			model.addAttribute("keyword", search);
			if (request.getParameter("letter") != null) {
				request.setAttribute("letter", request.getParameter("letter"));
			}

			if (type.equals("cas")) {
				request.setAttribute("cas", 1);
				request.setAttribute("name", 0);
			} else {
				request.setAttribute("cas", 0);
				request.setAttribute("name", 1);
			}

			return "reagents :: reagentTable";

		} catch (Exception e) {
			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while search = {}", search);
			return null;
		}
	}

	@RequestMapping(value = {"/reagents/{id_str}"}, method = RequestMethod.GET)
	public ModelAndView reagent(HttpServletRequest request, @PathVariable String id_str, HttpServletResponse response)
			throws IOException, ServletException {
		ModelAndView model = new ModelAndView("reagent");
		try {
			Long id = Long.parseLong(id_str);
			request.setAttribute("menu", pageService.getMenu("main"));
			Reagent reagent = reagentService.get(id);
			if (reagent != null) {
				request.setAttribute("reagent", reagent);
			} else {
				request.getRequestDispatcher("/reagents").forward(request, response);
			}

			return model;
		} catch (Exception e) {
			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while getting page id = {}", id_str);
			return null;
		}

	}

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ModelAndView model = new ModelAndView("page");
		try {
			request.setAttribute("menu", pageService.getMenu("main"));
			request.setAttribute("page", pageService.getByName("main"));
			return model;
		} catch (Exception e) {

			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while opened home page");
			return null;
		}

	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
							  @RequestParam(value = "logout", required = false) String logout,
							  HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
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
			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while singin");
			return null;
		}
	}

	@RequestMapping(value = {"/page/{name}"}, method = RequestMethod.GET)
	public ModelAndView pages(HttpServletRequest request, @PathVariable String name, HttpServletResponse response)
			throws IOException, ServletException {
		ModelAndView model = new ModelAndView("page");
		try {
			List list = pageService.getMenu(name);
			if (pageService.getByName(name) != null) {
				request.setAttribute("color", list.get(list.size() - 1));
				list.remove(list.size() - 1);
				request.setAttribute("menu", list);
				request.setAttribute("page", pageService.getByName(name));
			} else {
				request.getRequestDispatcher("/").forward(request, response);
			}

			return model;
		} catch (Exception e) {
			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while opened page = {}", name);
			return null;
		}

	}

	@RequestMapping(value = {"/medications"}, method = RequestMethod.GET)
	public ModelAndView medications(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws IOException, ServletException {
		ModelAndView model = new ModelAndView("reagents");

		try {

			String kind = "%Medication%";

			Page<Reagent> page = reagentService.getPage(kind, new PageRequest(0, 50));

			paginationModel.setRows(page.getTotalElements());

			paginationModel.setPage(request.getParameter("page"));

			paginationModel.setNumber(request.getParameter("number"));
			model.addObject("title", "Лекарства");
			if (paginationModel.getPage() > paginationModel.getPagesize()) {
				paginationModel.setPage(String.valueOf(paginationModel.getPagesize()));
			}
			model.addObject("reagentList", page);

			if (paginationModel.getPage() < 20) {
				paginationModel.setPage("20");
			}
			model.addObject("page", paginationModel);

			List list = pageService.getMenu("medications");
			if (pageService.getByName("medications") != null) {
				request.setAttribute("color", list.get(list.size() - 1));
				list.remove(list.size() - 1);
				request.setAttribute("menu", list);
			} else {
				request.getRequestDispatcher("/").forward(request, response);
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

			return model;
		} catch (Exception e) {
			request.getRequestDispatcher("/").forward(request, response);
			logger.error("while opened medicaments ");
			return null;
		}

	}

	@RequestMapping(value = {"/medications/{id_str}"}, method = RequestMethod.GET)
	public ModelAndView medication(HttpServletRequest request, @PathVariable String id_str,
								   HttpServletResponse response) throws IOException, ServletException {
		ModelAndView model = new ModelAndView("reagent");
		try {
			Long id = Long.parseLong(id_str);
			request.setAttribute("menu", pageService.getMenu("main"));
			if (reagentService.get(id) != null) {
				request.setAttribute("reagent", reagentService.get(id));
			} else {
				request.getRequestDispatcher("/reagents").forward(request, response);
			}
			return model;
		} catch (Exception e) {
			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while opened id = {}", id_str);
			return null;
		}

	}

	@Autowired
	Pars pars;

	@RequestMapping(value = {"/pars"}, method = RequestMethod.GET)
	public void pars() throws IOException, ServletException {
		ModelAndView model = new ModelAndView("pars");
		pars.pars();
	}

	@RequestMapping(value = {"/sitemap.xml"}, method = RequestMethod.GET)
	public ModelAndView sitemap(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ModelAndView model = new ModelAndView("sitemap");

		try {
			List<Reagent> a = reagentService.getAll("ChemicalAgent");

			request.setAttribute("reagents", reagentService.getAll("ChemicalAgent"));
			request.setAttribute("pages", pageService.getAll());
			request.setAttribute("medications", reagentService.getAll("Medication"));
			return model;
		} catch (Exception e) {
			logger.error("while getting sitemap");
		}

		return null;
	}

	@RequestMapping(value = {"/chemical_agents"}, method = RequestMethod.GET)
	public ModelAndView chemical_agents(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ModelAndView model = new ModelAndView("reagent");
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("menu", pageService.getMenu("main"));
			if (reagentService.get(id) != null) {
				request.setAttribute("reagent", reagentService.get(id));
			} else {
				request.getRequestDispatcher("/reagents").forward(request, response);
			}
			return model;
		} catch (Exception e) {
			request.getRequestDispatcher("/reagents").forward(request, response);
			logger.error("while getting chemical_agents");
			return null;
		}

	}

}

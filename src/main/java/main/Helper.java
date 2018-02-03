package main;

import main.model.Reagent;
import main.service.PageService;
import main.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class Helper {

	private static PageService pageService;

	private static ReagentService reagentService;

	@Autowired
	public Helper(PageService pageService, ReagentService reagentService) {
		this.pageService = pageService;
		this.reagentService = reagentService;
	}

	public static int getBeginPage(int totalPageCount, int current) {
		if (totalPageCount > 5) {
			return Math.max(1, current - 5);

		} else {
			return Math.max(1, current - totalPageCount);
		}
	}

	public static int getEndPage(int totalPageCount, int begin) {
		if (totalPageCount < begin) {
			return begin;
		}
		if (totalPageCount > 5) {

			return Math.min(begin + 5, totalPageCount);
		} else {
			return Math.min(begin + totalPageCount, totalPageCount);
		}
	}

	public static ModelAndView getMenu(ModelAndView model, String pageName) {
		List list = pageService.getMenu(pageName);
		if (pageService.getByName(pageName) != null) {
			model.addObject("color", list.get(list.size() - 1));
			list.remove(list.size() - 1);
			model.addObject("menu", list);
			return model;
		} else {
			return null;
		}
	}

	public static ModelAndView getTitle(ModelAndView model, String kind) {
		if (kind == null) {
			return model;
		}
		switch (kind) {
			case "CatalogObject|ChemicalAgent|": {
				return model.addObject("title", "Реактивы");
			}
			case "CatalogObject|Medication|": {
				return model.addObject("title", "Лекарства");
			}
			default:
				return model;
		}
	}

	public static HttpSession removeSearchSession(HttpSession session) {
		session.setAttribute("pageNumber", null);
		session.setAttribute("search", null);
		session.setAttribute("type", null);
		session.setAttribute("kind", null);
		return session;
	}

	public static Page<Reagent> checkSearchParametrs(String search, String type, String kind, Integer pageNumber) {
		if (search != null && type != null) {
			if (kind == null) {
				kind = "%%";
			} else {
				kind = "%" + kind + "%";
			}

			if (pageNumber == null || pageNumber <= 0) {
				pageNumber = 1;
			}
			return reagentService.search(search, type, kind, new PageRequest(pageNumber - 1, 50));
		} else {
			kind = "%ChemicalAgent%";
			return reagentService.getPage(kind, new PageRequest(0, 50));
		}
	}

	public static String checkType(String type, String search) {
		if (type == null) {
			return "name";
		}
		if (!type.equals("name") && !type.equals("cas") && !type.equals("alph")) {
			return "name";
		}
		if (type.equals("alph") & search.length() > 1) {
			return "name";
		}
		return type;

	}

	public static String checkSearch(String search){
		search = search.trim();
		if (search.equals("")) {
			search = "%%";
		}
		return search;
	}
}

package main;

import main.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Service
public class Helper {

	private static PageService pageService;

	@Autowired
	public Helper(PageService pageService) {this.pageService = pageService;}

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
		if (kind == null){
			return model;
		}
		switch (kind) {
			case "CatalogObject|ChemicalAgent|" : {
				return model.addObject("title", "Реактивы");
			}
			case "CatalogObject|Medication|": {
				return model.addObject("title", "Лекарства");
			}
			default: return model;
		}
	}
}

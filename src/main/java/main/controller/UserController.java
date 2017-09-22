package main.controller;

import main.model.Page;
import main.model.Reagent;
import main.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ReagentService reagentService;

    @Autowired
    private Page pageModel;

    private int size = reagentService.getAll().size();


    @RequestMapping(value = {"/reagents"}, method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("reagents");

        //pageModel.setRows(reagentService.getAll().size());
        pageModel.setRows(size);
        pageModel.setPage(request.getParameter("page"));
        pageModel.setNumber(request.getParameter("number"));

        if (pageModel.getPage() > pageModel.getPagesize()) {
            pageModel.setPage(String.valueOf(pageModel.getPagesize() + 2));
        }
            request.setAttribute("reagentList", reagentService.getPage(pageModel.getNumber() * 50));
        request.setAttribute("page", pageModel);

        return model;
    }


    @RequestMapping(value = {"/reagents/search"}, method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("search");
        String search = request.getParameter("keyword");

        request.setAttribute("reagentList", reagentService.searchByName(search));
        request.setAttribute("page", pageModel);


        request.setAttribute("keyword", search);

        return model;
    }

    @RequestMapping(value = {"/max"}, method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request, @PathVariable long id) throws IOException {
        ModelAndView model = new ModelAndView("test");

        return model;
    }

    @RequestMapping(value = {"/reagents/{id}"}, method = RequestMethod.GET)
    public ModelAndView reagent(HttpServletRequest request, @PathVariable long id) throws IOException {
        ModelAndView model = new ModelAndView("reagent");
        request.setAttribute("reagent", reagentService.get(id));
        return model;
    }

    @RequestMapping(value = {"/periodic"}, method = RequestMethod.GET)
    public ModelAndView periodic(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("periodic");

        return model;
    }

    @RequestMapping(value = {"/supplements"}, method = RequestMethod.GET)
    public ModelAndView supplements(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("supplements");

        return model;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("main");

        return model;
    }

    @RequestMapping(value = {"/supplement/colors"}, method = RequestMethod.GET)
    public ModelAndView color(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("supp/colors");

        return model;
    }

    @RequestMapping(value = {"/supplement/conservant"}, method = RequestMethod.GET)
    public ModelAndView conservant(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("supp/conservant");

        return model;
    }

}

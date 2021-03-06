package main.controller;

import main.model.Page;
import main.model.Pagination;
import main.model.Reagent;
import main.model.User;
import main.service.PageService;
import main.service.ReagentService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private ReagentService reagentService;

    @Autowired
    private Pagination paginationModel;

    @Autowired
    private PageService pageService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/reagents"}, method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("reagents");

        paginationModel.setRows(reagentService.getCount());
        paginationModel.setPage(request.getParameter("page"));
        paginationModel.setNumber(request.getParameter("number"));

        if (paginationModel.getPage() > paginationModel.getPagesize()) {
            paginationModel.setPage(String.valueOf(paginationModel.getPagesize() ));
        }
            request.setAttribute("reagentList", reagentService.getPage(paginationModel.getNumber() * 50));

        if (paginationModel.getPage() < 20) {
            paginationModel.setPage("20");
        }
        request.setAttribute("page", paginationModel);
        request.setAttribute("menu", pageService.getMenu("main"));

        return model;
    }


    @RequestMapping(value = {"/reagents/search"}, method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("search");
        String search = request.getParameter("keyword");
        String type = request.getParameter("searchType");
        List<Reagent> list = reagentService.searchByName(search,type);
        paginationModel.setRows((long) list.size());
        request.setAttribute("reagentList", list);
        request.setAttribute("page", paginationModel);
        request.setAttribute("menu", pageService.getMenu("main"));
        request.setAttribute("keyword", search);
        if (type.equals("cas")){
            request.setAttribute("cas", 1);
            request.setAttribute("name", 0);
        }
        else {
            request.setAttribute("cas", 0);
            request.setAttribute("name", 1);
        }



        return model;
    }


    @RequestMapping(value = {"/reagents/{id}"}, method = RequestMethod.GET)
    public ModelAndView reagent(HttpServletRequest request, @PathVariable long id) throws IOException {
        ModelAndView model = new ModelAndView("reagent");
        request.setAttribute("menu", pageService.getMenu("main"));
        request.setAttribute("reagent", reagentService.get(id));
        return model;
    }



    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("page");
        request.setAttribute("menu", pageService.getMenu("main"));
        request.setAttribute("page", pageService.getByName("main") );
        return model;
    }



    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout){
        ModelAndView model = new ModelAndView();


        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }


    @RequestMapping(value = {"/page/{name}"}, method = RequestMethod.GET)
    public ModelAndView pages(HttpServletRequest request, @PathVariable String name) throws IOException {
        ModelAndView model = new ModelAndView("page");
        List list = pageService.getMenu(name);

        request.setAttribute("color", list.get(list.size()-1));
        list.remove(list.size()-1);
        request.setAttribute("menu", list);
        request.setAttribute("page", pageService.getByName(name) );

        return model;
    }


}

package main.controller;

import main.model.Page;
import main.model.Pagination;
import main.model.Reagent;
import main.model.User;
import main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
    public ModelAndView mainGet(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView model = new ModelAndView("reagents");
        try {

            String kind = "ChemicalAgent";
            paginationModel.setRows(reagentService.getCount(kind));
            paginationModel.setPage(request.getParameter("page"));
            paginationModel.setNumber(request.getParameter("number"));
            request.setAttribute("title", "Реактивы");
            if (paginationModel.getPage() > paginationModel.getPagesize()) {
                paginationModel.setPage(String.valueOf(paginationModel.getPagesize()));
            }
            request.setAttribute("reagentList", reagentService.getPage(paginationModel.getNumber() * 50, kind));

            if (paginationModel.getPage() < 20) {
                paginationModel.setPage("20");
            }
            request.setAttribute("page", paginationModel);

            String pageName = "reagents";
            List list = pageService.getMenu(pageName);
            if ( pageService.getByName(pageName) != null) {
                request.setAttribute("color", list.get(list.size()-1));
                list.remove(list.size()-1);
                request.setAttribute("menu", list);
            }
            else {
                request.getRequestDispatcher("/").forward(request, response);
            }
            return model;
        }
        catch (Exception e){
            request.getRequestDispatcher("/").forward(request,response);
            return null;
        }

    }


    @RequestMapping(value = {"/reagents/search"}, method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView model = new ModelAndView("search");
        try {
            String type = request.getParameter("searchType");
            String search = request.getParameter("keyword");
            if ( type == null| search == null) {
                response.sendRedirect("/reagents");
                return null;
            }
            if (search.equals("")| type.equals("")) {
                response.sendRedirect("/reagents");
                return null;
            }


            List <Reagent> list = new ArrayList <>();

            list = reagentService.searchByName(search, type);
            paginationModel.setRows((long) list.size());
            request.setAttribute("reagentList", list);
            request.setAttribute("page", paginationModel);
            request.setAttribute("menu", pageService.getMenu("main"));
            request.setAttribute("keyword", search);
            if (request.getParameter("letter") != null){
                request.setAttribute("letter",request.getParameter("letter") );
            }

            if (type.equals("cas")) {
                request.setAttribute("cas", 1);
                request.setAttribute("name", 0);
            } else {
                request.setAttribute("cas", 0);
                request.setAttribute("name", 1);
            }

            return model;
        }
        catch (Exception e) {
            request.getRequestDispatcher("/reagents").forward(request,response);
            return null;
        }
    }


    @RequestMapping(value = {"/reagents/{id_str}"}, method = RequestMethod.GET)
    public ModelAndView reagent(HttpServletRequest request, @PathVariable String id_str, HttpServletResponse response) throws IOException, ServletException {
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
        }
        catch (Exception e) {
            request.getRequestDispatcher("/reagents").forward(request, response);
            return null;
        }

    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView model = new ModelAndView("page");
        try {
            request.setAttribute("menu", pageService.getMenu("main"));
            request.setAttribute("page", pageService.getByName("main") );
            return   model;
        }
        catch (Exception e){

            request.getRequestDispatcher("/reagents").forward(request, response);
            return null;
        }


    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
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
        }
        catch (Exception e) {
            request.getRequestDispatcher("/reagents").forward(request, response);
            return null;
        }
    }


    @RequestMapping(value = {"/page/{name}"}, method = RequestMethod.GET)
    public ModelAndView pages(HttpServletRequest request, @PathVariable String name,HttpServletResponse response) throws IOException, ServletException {
        ModelAndView model = new ModelAndView("page");
        try {
            List list = pageService.getMenu(name);
            if ( pageService.getByName(name) != null) {
                request.setAttribute("color", list.get(list.size()-1));
                list.remove(list.size()-1);
                request.setAttribute("menu", list);
                request.setAttribute("page", pageService.getByName(name) );
            }
            else {
                request.getRequestDispatcher("/").forward(request, response);
            }

            return model;
        }
        catch (Exception e) {
            request.getRequestDispatcher("/reagents").forward(request, response);
            return null;
        }

    }


    @RequestMapping(value = {"/medications"}, method = RequestMethod.GET)
    public ModelAndView medications(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView model = new ModelAndView("reagents");
        try {
            String kind = "Medication";
            paginationModel.setRows(reagentService.getCount(kind));
            paginationModel.setPage(request.getParameter("page"));
            paginationModel.setNumber(request.getParameter("number"));



            if (paginationModel.getPage() > paginationModel.getPagesize()) {
                paginationModel.setPage(String.valueOf(paginationModel.getPagesize()));
            }
            request.setAttribute("reagentList", reagentService.getPage(paginationModel.getNumber() * 50, kind));

            if (paginationModel.getPage() < 20) {
                paginationModel.setPage("20");
            }
            request.setAttribute("page", paginationModel);
            request.setAttribute("title", "Лекарства");

            List list = pageService.getMenu("medications");
            if ( pageService.getByName("medications") != null) {
                request.setAttribute("color", list.get(list.size()-1));
                list.remove(list.size()-1);
                request.setAttribute("menu", list);
            }
            else {
                request.getRequestDispatcher("/").forward(request, response);
            }
            return model;
        }
        catch (Exception e){
            request.getRequestDispatcher("/").forward(request,response);
            return null;
        }

    }


    @RequestMapping(value = {"/medications/{id_str}"}, method = RequestMethod.GET)
    public ModelAndView medication(HttpServletRequest request, @PathVariable String id_str, HttpServletResponse response) throws IOException, ServletException {
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
        }
        catch (Exception e) {
            request.getRequestDispatcher("/reagents").forward(request, response);
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
    public ModelAndView sitemap(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView model = new ModelAndView("sitemap");

        List<Reagent> a = reagentService.getAll("ChemicalAgent");

        request.setAttribute("reagents", reagentService.getAll("ChemicalAgent"));
        request.setAttribute("pages", pageService.getAll());
        request.setAttribute("medications", reagentService.getAll("Medication"));
        return model;

    }


    @RequestMapping(value = {"/chemical_agents"}, method = RequestMethod.GET)
    public ModelAndView chemical_agents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        }
        catch (Exception e) {
            request.getRequestDispatcher("/reagents").forward(request, response);
            return null;
        }

    }

    @Autowired
    EmailServiceImpl emailService;

    @RequestMapping(value = {"/admin/sendmail"}, method = RequestMethod.GET)
    public void test() throws IOException, ServletException, MessagingException {
        ModelAndView model = new ModelAndView("test");
        emailService.sendEmail("arcas.llc@yandex.ru","proberka","Тексттписьма");
    }




}

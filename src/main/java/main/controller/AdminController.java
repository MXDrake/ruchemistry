package main.controller;

import main.model.Page;
import main.model.Pagination;
import main.service.PageService;
import main.service.ReagentService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private ReagentService reagentService;

    @Autowired
    private Pagination paginationModel;

    @Autowired
    private PageService pageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView testpage(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("admin");


        return model;
    }


    @RequestMapping(value = {"/admin/addpage"}, method = RequestMethod.POST)
    public ModelAndView addPage(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("admin");
        Page page = new Page();
        try {
            page.setContent(request.getParameter("content"));
            page.setDescription(request.getParameter("description"));
            page.setKeywords(request.getParameter("keywords"));
            page.setTitle(request.getParameter("title"));
            page.setName(request.getParameter("name"));
            page.setMenu(Integer.parseInt(request.getParameter("menu")));
            request.setAttribute("message", "Succesfull");

            pageService.addPage(page);
        }
        catch (Exception e) {
            request.setAttribute("message", "Error");
        }



        return model;
    }
}

package main.controller;

import main.model.Page;
import main.model.Reagent;
import main.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = {"/reagents"}, method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("reagents");

        pageModel.setRows(reagentService.getAll().size());
        //pageModel.setPagesize(pageModel. / 10);

        pageModel.setPage(request.getParameter("page"));
        pageModel.setNumber(request.getParameter("number"));

        if (pageModel.getPage() > pageModel.getPagesize()) {
            pageModel.setPage(String.valueOf(pageModel.getPagesize() + 2));
        }

        request.setAttribute("reagentList", reagentService.getPage(pageModel.getNumber() * 10));
        request.setAttribute("reagent", reagentService.get(35l));
        request.setAttribute("page", pageModel);

        return model;
    }

    @RequestMapping(value = {"/max"}, method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("test");

        return model;
    }

    @RequestMapping(value = {"/reagent"}, method = RequestMethod.GET)
    public ModelAndView reagent(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("reagent");

        return model;
    }

}

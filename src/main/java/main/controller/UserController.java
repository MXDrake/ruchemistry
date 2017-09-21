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
    ReagentService reagentService;

    @Autowired
    Page pageModel;


    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView("main");

        pageModel.setPagesize(reagentService.getAll().size()/10);

        pageModel.setPage(request.getParameter("page"));
        pageModel.setNumber(request.getParameter("number"));

        if (pageModel.getPage()>pageModel.getPagesize()) {
            pageModel.setPage(String.valueOf(pageModel.getPagesize()+2));
        }

        request.setAttribute("reagentList",reagentService.getPage(pageModel.getNumber()*10));
        request.setAttribute("reagent",reagentService.get(35l));

        request.setAttribute("page", pageModel);
       // request.setAttribute("page", pageModel.getPage());
       // request.setAttribute("pagesize", pageModel.getPagesize());
        String str ="<a  th:href=\"@{?number=}+${page.getPage()-10}+@{&page=}+${page.getPage()}\" th:text=\"${page.getPage()-9}\">test</a>";
        request.setAttribute("navpage", str);
        return model;
    }


}

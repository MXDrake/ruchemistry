package main.controller;

import main.model.Reagent;
import main.service.ReagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    ReagentService reagentService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView users = new ModelAndView("main");
        Reagent reagent = reagentService.get(35l);

        request.setAttribute("reagent",reagentService.get(35l));
        return users;
    }
}

package main.controller;

import main.model.Page;
import main.model.Pagination;
import main.model.Reagent;
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
    public ModelAndView admin(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("admin");
        return model;
    }

    @RequestMapping(value = {"/admin/page"}, method = RequestMethod.GET)
    public ModelAndView admingage(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("adminpage");
        return model;
    }


    @RequestMapping(value = {"/admin/getpage"}, method = RequestMethod.POST)
    public ModelAndView getpage(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("adminpage");
        if (request.getParameter("get_name") != null) {
            Page page = pageService.getByName(request.getParameter("get_name"));
            request.setAttribute("page", page);

        }

        return model;
    }


    @RequestMapping(value = {"/admin/addpage"}, method = RequestMethod.POST)
    public ModelAndView addPage(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("adminpage");
        Page page = new Page();
        try {
            page.setContent(request.getParameter("content"));
            page.setDescription(request.getParameter("description"));
            page.setKeywords(request.getParameter("keywords"));
            page.setTitle(request.getParameter("title"));
            page.setName(request.getParameter("name"));
            page.setPath(request.getParameter("path"));
            page.setMenu(Integer.parseInt(request.getParameter("menu")));
            request.setAttribute("message", "Succesfull");

            pageService.addPage(page);
        }
        catch (Exception e) {
            request.setAttribute("message", "Error");
        }
        return model;
    }


    @RequestMapping(value = {"/admin/editpage"}, method = RequestMethod.POST)
    public ModelAndView editpage(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("adminpage");

        Page page = pageService.getPage(Long.parseLong(request.getParameter("id")));

        try {
            page.setContent(request.getParameter("content"));
            page.setDescription(request.getParameter("description"));
            page.setKeywords(request.getParameter("keywords"));
            page.setTitle(request.getParameter("title"));
            page.setName(request.getParameter("name"));
            page.setMenu(Integer.parseInt(request.getParameter("menu")));
            page.setPath(request.getParameter("path"));
            request.setAttribute("message", "Succesfull");


            pageService.update(page);
        }
        catch (Exception e) {
            request.setAttribute("message", "Error");
        }

        return model;
    }

    @RequestMapping(value = {"/admin/getreagent"}, method = RequestMethod.POST)
    public ModelAndView getreagent(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("admin");
        if (request.getParameter("get_id") != null) {
            Long id = Long.valueOf(request.getParameter("get_id"));
            Reagent reagent = reagentService.get(id);
            request.setAttribute("r_name", reagent.getName() );
            request.setAttribute("r_id", reagent.getId() );
            request.setAttribute("r_content", reagent.getContent() );
            request.setAttribute("r_cas", reagent.getCas() );
            request.setAttribute("r_eng", reagent.getEngName() );
            request.setAttribute("r_weight", reagent.getWeight() );
            request.setAttribute("r_k", reagent.getTmp_k() );
            request.setAttribute("r_p", reagent.getTmp_p() );

        }
        return model;
    }


    @RequestMapping(value = {"/admin/editreagent"}, method = RequestMethod.POST)
    public ModelAndView editreagent(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("admin");
        if (request.getParameter("r_id") != null){
            Long id = Long.parseLong(request.getParameter("r_id"));
            Reagent reagent = reagentService.get(id);
            reagent.setName(request.getParameter("r_name"));
            reagent.setContent(request.getParameter("r_content"));
            reagent.setCas(request.getParameter("r_cas"));
            reagent.setEngName(request.getParameter("r_eng"));
            reagent.setTmp_p(request.getParameter("r_p"));
            reagent.setTmp_k(request.getParameter("r_k"));
            reagent.setWeight(request.getParameter("r_weight"));

            reagentService.update(reagent);
        }
        return model;
    }

    @RequestMapping(value = {"/admin/addreagent"}, method = RequestMethod.POST)
    public ModelAndView addreagent(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("admin");
        Reagent reagent = new Reagent();

            reagent.setName(request.getParameter("name"));
           /* reagent.setCas(request.getParameter("cas"));
            reagent.setContent(request.getParameter("content"));
            reagent.setD204(request.getParameter("d204"));
            reagent.setEngName(request.getParameter("engName"));
            reagent.setSinonim(request.getParameter("sinonim"));
            reagent.setWeight(request.getParameter("weight"));
            reagent.setImage(request.getParameter("image"));
            reagent.setLink(request.getParameter("link"));
            reagent.setwDissolubility(request.getParameter("wDissolubility"));
            reagent.setvDissolubility(request.getParameter("vDissolubility"));
            reagent.setTmp_k(request.getParameter("tmp_k"));
            reagent.setTmp_p(request.getParameter("tmp_p"));
            */
            reagentService.add(reagent);




        return model;
    }

}

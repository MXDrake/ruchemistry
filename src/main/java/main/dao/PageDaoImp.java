package main.dao;

import main.model.Page;
import main.model.Reagent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PageDaoImp extends AbstractDaoImpl implements PageDao {
    @Override
    public Page getPage(Long id) {
        try {
            Page page = entityManager.find(Page.class,id);
            return page;
        }
       catch (Exception e){
            return  null;
       }
    }

    @Override
    public Page getByName(String name) {
        try {
            Page page = (Page) entityManager.createQuery("FROM Page WHERE name =:NAME ").setParameter("NAME", name).getSingleResult();
            return page;
        }
        catch (Exception e) {
            return  null;
        }


    }

    @Override
    public List getMenu(String name) {
        List<String> result = new ArrayList <>();
        try {
            List<Page> list = entityManager.createQuery("FROM Page WHERE menu =:MENU  ORDER BY name").setParameter("MENU", 1).getResultList();
            int j =1;
            String color="";

            if (name.equals("main")) {
                for (int i = 0; i < list.size(); i++) {
                    j++;
                    result.add("<a class=\"opt"+ j +"\" href=" + list.get(i).getPath() + list.get(i).getName() +">" + list.get(i).getTitle()+ "</a>");
                }
                return result;
            }

            for (int i = 0; i < list.size(); i++) {
                j++;

                if ( !list.get(i).getName().equals(name)) {

                        result.add("<a class=\"opt"+ j +"\" href=" + list.get(i).getPath() + list.get(i).getName() +">" + list.get(i).getTitle()+ "</a>");


                }
                else {
                    switch (i+1){
                        case 1: color = "background-color: #00bf79";
                            break;
                        case 2: color = "background-color: #c53a99";
                            break;
                        case 3: color = "background-color: #0097c3";
                            break;
                        case 4: color = "background-color: #d03639";
                            break;
                        case 5: color = "background-color: #99ab00";
                            break;
                        case 6: color = "background-color: #359A00";
                            break;
                        case 7: color = "background-color: #7f51c4";
                            break;
                        default: color = "background-color: #c53a99";
                    }
                    result.add("<a class=\"opt"+ j +" hover \" href=" + list.get(i).getPath() + list.get(i).getName() +">" + list.get(i).getTitle()+ "</a>");
                }

            }

            result.add(color);
        }
        catch (Exception e) {

        }

        return result;
    }


}










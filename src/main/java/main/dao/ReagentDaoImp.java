package main.dao;

import main.model.Reagent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ReagentDaoImp extends AbstractDaoImpl implements ReagentDao {


    public Reagent get(long id) {
        try {
            Reagent reagent = entityManager.find(Reagent.class,id);
            return reagent;
        }
        catch (Exception e) {

        }
       return null;
    }


    public List<Reagent> getAll(String kind) {
        try {
            kind = "%"+kind+"%";
            List<Reagent> list = entityManager.createQuery("FROM Reagent WHERE kind like :KIND  order by name").setParameter("KIND", kind) .getResultList();
            return list;
        }
        catch (Exception e) {

        }
       return null;
    }

    public List<Reagent> getPage(int firstResult, String kind) {
        kind = "%"+kind+"%";
        try {
            List<Reagent> list = entityManager.createQuery("FROM Reagent WHERE kind like :KIND ORDER BY  name").setParameter("KIND", kind).setFirstResult(firstResult).setMaxResults(50).getResultList();
            return list;
        }
        catch (Exception e) {

        }
        return null;
    }


    public List <Reagent> searchByName(String value, String type) {
        value = "%" + value +"%";
        List<Reagent> list = new ArrayList <>();
        if (type.equals("name")) {
            list = entityManager.createQuery("FROM Reagent WHERE name like :NAME or sinonim like :NAME or engName like :NAME ORDER BY name").setParameter("NAME", value) .getResultList();
        }
        else {
            list = entityManager.createQuery("FROM Reagent WHERE cas like :CAS  ORDER BY name").setParameter("CAS", value) .getResultList();

        }

        return list;
    }


    public Long getCount(String kind) {
        Long count;
        kind = "%"+kind+"%";
        try {
            count = (Long) entityManager.createQuery("SELECT COUNT(*) FROM Reagent WHERE kind like :KIND").setParameter("KIND", kind).getSingleResult();
        }
        catch (Exception e) {
            count = 0l;
        }
        return count ;
    }
}

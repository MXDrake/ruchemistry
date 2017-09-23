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
        Reagent reagent = entityManager.find(Reagent.class,id);
        return reagent;
    }


    public List<Reagent> getAll() {
        List<Reagent> list = entityManager.createQuery("FROM Reagent order by name").getResultList();
        return list;
    }

    public List<Reagent> getPage(int firstResult) {
        List<Reagent> list = entityManager.createQuery("FROM Reagent order by name").setFirstResult(firstResult).setMaxResults(50).getResultList();
        return list;
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


    public Long getCount() {
        Long count;
        try {
            count = (Long) entityManager.createQuery("SELECT COUNT(*) FROM Reagent").getSingleResult();
        }
        catch (Exception e) {
            count = 0l;
        }
        return count ;
    }
}

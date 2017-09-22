package main.dao;
import main.model.Reagent;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class ReagentDaoImp extends AbstractDao implements ReagentDao {
    @Override
    public Reagent get(long id) {
        Reagent reagent = entityManager.find(Reagent.class,id);
        return reagent;
    }

    @Override
    public List<Reagent> getAll() {
        List<Reagent> list = entityManager.createQuery("FROM reagents order by name").getResultList();
        return list;
    }

    public List<Reagent> getPage(int firstResult) {
        List<Reagent> list = entityManager.createQuery("FROM reagents order by name").setFirstResult(firstResult).setMaxResults(50).getResultList();
        return list;
    }


}

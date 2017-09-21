package main.dao;
import main.model.Reagent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReagentDaoImp extends AbstractDao implements ReagentDao {
    @Override
    public Reagent get(long id) {
        Reagent reagent = entityManager.find(Reagent.class,id);
        return reagent;
    }
}

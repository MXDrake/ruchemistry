package main.service;

import main.dao.ReagentDao;
import main.model.Reagent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ReagentServiceImp implements ReagentService {
    @Autowired
    private ReagentDao reagentDao;

    @Override
    public Reagent get(long id){
        return reagentDao.get(id);

    }

    @Override
    public List<Reagent> getAll() {
        return reagentDao.getAll();
    }
    public List<Reagent> getPage(int firstResult){
        return  reagentDao.getPage(firstResult);
    }

    @Override
    public List <Reagent> searchByName(String value, String type) {
        return reagentDao.searchByName(value, type);
    }

    @Override
    public Long getCount() {
        return reagentDao.getCount();
    }

    @Override
    public void update(Reagent reagent) {
        reagentDao.update(reagent);
    }
}

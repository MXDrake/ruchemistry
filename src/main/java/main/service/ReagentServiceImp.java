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
    public List<Reagent> getAll(String kind) {
        return reagentDao.getAll(kind);
    }
    public List<Reagent> getPage(int firstResult, String kind){
        return  reagentDao.getPage(firstResult,kind);
    }

    @Override
    public List <Reagent> searchByName(String value, String type) {
        return reagentDao.searchByName(value, type);
    }

    @Override
    public Long getCount(String kind) {
        return reagentDao.getCount(kind);
    }

    @Override
    public void update(Reagent reagent) {
        reagentDao.update(reagent);
    }

    @Override
    public void add(Reagent reagent) {
        reagentDao.addModel(reagent);
    }
}

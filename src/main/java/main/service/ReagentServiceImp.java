package main.service;

import main.dao.ReagentDao;
import main.model.Reagent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReagentServiceImp implements ReagentService {
    @Autowired
    ReagentDao reagentDao;

    @Override
    public Reagent get(long id){
        return reagentDao.get(id);

    }
}

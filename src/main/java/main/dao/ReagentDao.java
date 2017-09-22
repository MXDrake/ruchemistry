package main.dao;

import main.model.*;

import java.util.List;

public interface ReagentDao {

    public Reagent get (long id );
    public List<Reagent> getAll();
    public List<Reagent> getPage(int firstResult);
    public List<Reagent> searchByName(String name);

}

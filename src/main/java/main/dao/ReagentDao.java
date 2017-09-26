package main.dao;

import main.model.*;

import java.util.List;

public interface ReagentDao extends AbstractDao {

    public Reagent get (long id );
    public List<Reagent> getAll(String kind);
    public List<Reagent> getPage(int firstResult,  String kind);
    public List<Reagent> searchByName(String value, String type);
    public Long getCount(String kind);
}

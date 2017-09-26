package main.service;

import main.model.Reagent;

import java.awt.print.Pageable;
import java.util.List;

public interface ReagentService {
    public Reagent get(long id);
    public List<Reagent> getAll(String kind);
    public List<Reagent> getPage(int firstResult, String kind);
    public List<Reagent> searchByName(String value, String type);
    public Long getCount(String kind);
    public void update( Reagent reagent);
    public void add (Reagent reagent);
}

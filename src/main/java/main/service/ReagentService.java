package main.service;

import main.model.Reagent;

import java.awt.print.Pageable;
import java.util.List;

public interface ReagentService {
    public Reagent get(long id);
    public List<Reagent> getAll();
    public List<Reagent> getPage(int firstResult);
    public List<Reagent> searchByName(String name);
}

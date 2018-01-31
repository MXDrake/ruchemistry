package main.service;

import main.model.Reagent;
import java.util.List;

public interface ReagentService {

	Reagent get(long id);
	List<Reagent> getAll(String kind);
	List<Reagent> getPage(int firstResult, String kind);
	List<Reagent> searchBy(String value, String type);
	Long getCount(String kind);
	void update(Reagent reagent);
	void add(Reagent reagent);
}

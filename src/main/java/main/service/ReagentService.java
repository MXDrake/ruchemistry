package main.service;

import main.model.Reagent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReagentService {

	Reagent get(long id);
	List<Reagent> getAll(String kind);
	Page<Reagent> getPage(String kind, Pageable page);
	Page<Reagent> search(String value, String type, String kind, Pageable pageable);
	void update(Reagent reagent);
	void add(Reagent reagent);
}

package main.service;

import main.model.Reagent;
import main.repository.ReagentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReagentServiceImp implements ReagentService {

	private ReagentRepository reagentRepository;

	@Autowired
	public ReagentServiceImp(ReagentRepository reagentRepository) {
		this.reagentRepository = reagentRepository;
	}

	@Override
	public Reagent get(long id) {
		return reagentRepository.getAllById(id);
	}

	@Override
	public List<Reagent> getAll(String kind) {
		kind = "%" + kind + "%";
		return reagentRepository.findAllByKindLikeOrderByName(kind);
	}

	@Override
	public Page<Reagent> search(String value, String type, String kind, Pageable pageable) {
		switch (type) {
			case "name": {
				value = "%" + value + "%";
				return reagentRepository.findByNameSinonimEng(value, kind, pageable);
			}
			case "cas": {
				value = "%" + value + "%";
				return reagentRepository.searchByCas(value, kind, pageable);
			}
			case "alph": {
				value = value + "%";
				return reagentRepository.searchByLetter(value, kind, pageable);
			}
		}
		return null;
	}

	@Override
	public void update(Reagent reagent) {
		reagentRepository.save(reagent);
	}

	@Override
	public void add(Reagent reagent) {
		reagentRepository.save(reagent);
	}

	@Override
	public void save(Reagent reagent) {
		reagentRepository.saveAndFlush(reagent);
	}

	public Page<Reagent> getPage(String kind, Pageable pageable) {
		return reagentRepository.findAllByKindLikeOrderByName(kind, pageable);
	}

}

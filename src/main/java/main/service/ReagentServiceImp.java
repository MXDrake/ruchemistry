package main.service;

import main.dao.ReagentDao;
import main.model.Reagent;
import main.repository.ReagentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReagentServiceImp implements ReagentService {

	private ReagentDao reagentDao;

	private ReagentRepository reagentRepository;

	@Autowired
	public ReagentServiceImp(ReagentDao reagentDao, ReagentRepository reagentRepository) {
		this.reagentRepository = reagentRepository;
		this.reagentDao = reagentDao;
	}

	@Override
	public Reagent get(long id) {
		return reagentRepository.getOne(id);
	}

	@Override
	public List<Reagent> getAll(String kind) {
		kind = "%" + kind + "%";
		return reagentRepository.findAllByKindLikeOrderByName(kind);
	}

	public List<Reagent> getPage(int firstResult, String kind) {
		return reagentDao.getPage(firstResult, kind);
	}

	@Override
	public List<Reagent> searchBy(String value, String type) {
		switch (type) {
			case "name": {
				value = "%" + value + "%";
				return reagentRepository.findAllByNameLikeOrSinonimLikeOrEngNameLikeOrderByName(value, value, value);
			}
			case "cas": {
				value = "%" + value + "%";
				return reagentRepository.findAllByCasLikeOrderByName(value);
			}
			case "alph": {
				value = value + "%";
				return reagentRepository.findAllByNameLikeOrderByName(value);
			}
		}
		return null;
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
				return  reagentRepository.searchByCas(value, kind, pageable);
			}
			case "alph": {
				value = value + "%";
				return reagentRepository.searchByLetter(value, kind, pageable);
			}
		}
		return null;

	}

	@Override
	public Long getCount(String kind) {
		return reagentDao.getCount(kind);
	}

	@Override
	public void update(Reagent reagent) {
		reagentRepository.save(reagent);
	}

	@Override
	public void add(Reagent reagent) {
		reagentRepository.save(reagent);
	}

	public Page<Reagent> getPage(String kind, Pageable pageable) {
		return reagentRepository.findAllByKindLikeOrderByName(kind, pageable);
	}

}

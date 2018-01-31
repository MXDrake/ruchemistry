package main.repository;

import main.model.Reagent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ReagentRepository extends JpaRepository<Reagent, Long> {

	List<Reagent> findAllByKindLikeOrderByName(String kind);

	List<Reagent> findAllByNameLikeOrSinonimLikeOrEngNameLikeOrderByName(String name, String sinonim, String engName);

	List<Reagent> findAllByCasLikeOrderByName(String cas);

	List<Reagent> findAllByNameLikeOrderByName(String name);
}

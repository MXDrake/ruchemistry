package main.repository;

import main.model.Reagent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ReagentRepository extends JpaRepository<Reagent, Long> {

	List<Reagent> findAllByKindLikeOrderByName(String kind);

	List<Reagent> findAllByNameLikeOrSinonimLikeOrEngNameLikeOrderByName(String name, String sinonim, String engName);

	@Query("SELECT r from Reagent r WHERE (r.name like :name OR r.sinonim like :name or r.engName like :name) AND r" +
		   ".kind like :kind " +
		   "ORDER BY r.name")
	Page<Reagent> findByNameSinonimEng(@Param("name") String name,@Param("kind") String kind, Pageable pageable);

	List<Reagent> findAllByCasLikeOrderByName(String cas);

	Page<Reagent> findAllByCasLikeAndKindOrderByName(String cas, String kind, Pageable pageable);

	List<Reagent> findAllByNameLikeOrderByName(String name);

	Page<Reagent> findAllByKindLikeOrderByName(String kind, Pageable pageable);

	Page<Reagent> findAllByNameLikeAndKindLikeOrderByName(String name ,String kind, Pageable pageable);

}

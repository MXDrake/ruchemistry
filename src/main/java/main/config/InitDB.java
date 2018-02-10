package main.config;

import main.service.GoodsService;
import main.service.ReagentService;
import main.service.RoleService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.ParseException;
@Component
public class InitDB {

	private UserService userService;
	
	private RoleService roleService;

	private ReagentService reagentService;

	private GoodsService goodsReagentService;


	@Autowired
	public InitDB(UserService userService, RoleService roleService, ReagentService reagentService,
				  GoodsService goodsReagentService) {
		this.userService = userService;
		this.roleService = roleService;
		this.reagentService = reagentService;
		this.goodsReagentService = goodsReagentService;
	}

	@PostConstruct
	public void createDB() throws ParseException {

//		Role adminRole = new Role("ADMIN");
//
//		Role userRole = new Role("USER");
//
//		roleService.save(adminRole);
//		roleService.save(userRole);
//
//		Set adminSet = new HashSet();
//
//		adminSet.add(adminRole);
//
//		User max = new User("Max", "123", true, "test@mail.com", adminSet);
//		userService.save(max);

//		Reagent reagent = new Reagent();
//		reagentService.save(reagent);
//		GoodsReagent goodsReagent = new GoodsReagent();
//		goodsReagent.setReagentId(reagent);
//		goodsReagentService.save(goodsReagent);
//		List<GoodsReagent> goodsReagentList = new ArrayList<>();
//		goodsReagentList.add(goodsReagent);
//		reagent.setGoodsReagent(goodsReagentList);
//		reagentService.save(reagent);
//
//		Reagent reagent1 = reagentService.get(7904);
//		GoodsReagent goodsReagent1 = goodsReagentService.get(2l);
//		Reagent reagent2 = reagentService.get(1);



	}
}

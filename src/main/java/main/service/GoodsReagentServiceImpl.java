package main.service;

import main.model.GoodsReagent;
import main.repository.GoodsReagentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsReagentServiceImpl implements GoodsService {

	private GoodsReagentRepository goodsReagentRepository;

	@Autowired
	public GoodsReagentServiceImpl(GoodsReagentRepository goodsReagentRepository) {
		this.goodsReagentRepository = goodsReagentRepository;
	}

	@Override
	public void save(GoodsReagent goodsReagent) {
		//goodsReagentRepository.save(goodsReagent);
		goodsReagentRepository.saveAndFlush(goodsReagent);
	}

	@Override
	public GoodsReagent get(Long id) {
		return goodsReagentRepository.findOne(id);
	}
}

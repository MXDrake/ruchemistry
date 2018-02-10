package main.service;

import main.model.GoodsReagent;
public interface GoodsService {

	void save (GoodsReagent goodsReagent);
	GoodsReagent get(Long id);
}

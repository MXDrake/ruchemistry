package main.service;

import main.dao.PageDao;
import main.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl  implements PageService{
    @Autowired
    PageDao pageDao;


    @Override
    public Page getPage(Long id) {
        return pageDao.getPage(id);
    }

    @Override
    public void addPage(Page page) {
     pageDao.addModel(page);
    }

    @Override
    public Page getByName(String name) {
        return pageDao.getByName(name);
    }

    @Override
    public List getMenu(String name) {
        return pageDao.getMenu(name);
    }

    @Override
    public void update(Page page) {
        pageDao.update(page);
    }

    @Override
    public List <Page> getAll() {
        return pageDao.getAll();
    }
}

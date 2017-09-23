package main.dao;

import main.model.Page;

import java.util.List;

public interface PageDao extends AbstractDao {

    public Page getPage(Long id);
    public Page getByName(String name);
    public List getMenu (String name);


}

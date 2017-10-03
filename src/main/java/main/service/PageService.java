package main.service;

import main.model.Page;

import java.util.List;

public interface PageService {
    public Page getPage(Long id);
    public void addPage(Page page);
    public Page getByName(String name);
    public List getMenu(String name);
    public void update (Page page);
    public List<Page> getAll();
}

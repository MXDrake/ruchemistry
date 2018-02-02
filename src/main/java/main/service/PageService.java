package main.service;

import main.model.Page;
import java.util.List;

public interface PageService {

    Page getPage(Long id);
    void addPage(Page page);
    Page getByName(String name);
    List getMenu(String name);
    void update (Page page);
    List<Page> getAll();
}

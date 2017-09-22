package main.model;

import main.dao.ReagentDao;
import main.service.ReagentService;
import main.service.ReagentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Page {

    //@Autowired
   // ReagentService reagentService;

    private int page;
    private int pagesize;
    private int number;
    private int rows;


    public Page(){
         this.page = 20;
        this.number = 0;
//        this.pagesize = reagentService.getAll().size() ;
    }

    public int getPage() {
        return page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public int getNumber() {
        return number;
    }

    public void setPage(String page) {
        if (page != null) {
            this.page = Integer.parseInt(page);
        }
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setNumber(String number) {
        if (number != null) {
            this.number = Integer.parseInt(number);
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        this.pagesize = rows/50;
    }
}

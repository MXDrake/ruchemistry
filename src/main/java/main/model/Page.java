package main.model;

import main.dao.ReagentDao;
import main.service.ReagentService;
import main.service.ReagentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Page {


    private int page;
    private int pagesize;
    private int number;


    public Page(){
         this.page = 10;
        this.number = 0;
       //this.pagesize = reagentService.getAll().size() / 10;
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
}

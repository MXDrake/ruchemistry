package main.service;

import main.dao.ReagentDao;
import main.model.Reagent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Service
public class Pars {

    @Autowired
    ReagentDao reagentDao;

    public void  pars() throws IOException {
      Document document = Jsoup.connect("http://www.himreakt.ru/47/").get();




        String html = document.getElementsByClass("lemma-main-content").html();

        Document doc = Jsoup.parse(html);

        String text = doc.body().text(); // "An example link"





        String[] mass = text.split("\\Идентификация Название|\\Регистрационный номер CAS|\\Молекулярная формула|\\Молекулярный вес|\\ InChI InChI=|\\Химические и физические свойства Плотность|\\" +
                "Точка кипения|\\ Точка плавления|\\Температура вспышки|\\Показатель преломления|\\Риски, безопасность и условия использования Указания по безопасности Код опасности Xi Указания по риску |\\Указания по безопасности|\\Указания по риску|\\Чтобы купить");

        String name = "";
        String cas = "";
        String formula = "";
        String weight = "";
        String plotnostb = "";
        String tmp_k = "";
        String tmp_p = "";
        String risk_bezopasn = "";
        String ss = "";


        if (mass.length >= 1){
             name = mass[1];
        }

        if (mass.length >= 2){
          cas = mass[2];

        }
        if (mass.length >= 3) {
            formula = mass[3];
        }

        if (mass.length >= 4) {
             weight = mass[4];
        }
       // String InChI = mass[5];
        if (mass.length >= 6) {
             plotnostb = mass[6];
        }
        if (mass.length >= 7) {
             tmp_k = mass[7];
        }

        if (mass.length >= 8) {
             tmp_p = mass[8];
        }
       // String tochka_vcpushki = mass[9];
        //String prelomlenie = mass[10];
        if (mass.length >= 11) {
             risk_bezopasn = mass[11];
        }

        if (mass.length >= 12) {
             ss = mass[12];
        }

        List<Reagent> list = reagentDao.searchByName(cas,"cas");

        if (list.size() == 0) {


            Reagent reagent = new Reagent();

            reagent.setName(name);
            reagent.setCas(cas);
            reagent.setFormula(formula);
            reagent.setWeight(weight);
            reagent.setTmp_k(tmp_k);
            reagent.setTmp_p(tmp_p);
            reagent.setRs(risk_bezopasn);
            reagent.setSs(ss);
            reagent.setD204(plotnostb);

            reagentDao.addModel(reagent);


        }
    }
}

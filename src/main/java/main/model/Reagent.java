package main.model;

import javax.persistence.*;

@Entity
@Table(name="reagents")
public class Reagent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sinonim_name")
    private String sinonim;

    @Column(name = "english_name")
    private String engName;

    @Column(name = "cas")
    private String cas;

    @Column(name = "neorg")
    private String link;

    @Column(name = "image")
    private String image;

    @Column(name = "receipt")
    private String content;

    @Column(name = "water_dissolubility")
    private String wDissolubility;

    @Column(name = "vehicle_dissolubility")
    private String vDissolubility;

    @Column(name = "molecular_weight")
    private String weight;

    @Column(name = "t_pl")
    private String tmp_p;

    @Column(name = "t_kp")
    private String tmp_k;

    @Column(name = "d204")
    private String d204;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getSinonim() {
        return sinonim;
    }

    public String getEngName() {
        return engName;
    }

    public String getCas() {
        return cas;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getwDissolubility() {
        return wDissolubility;
    }

    public String getvDissolubility() {
        return vDissolubility;
    }

    public String getWeight() {
        return weight;
    }

    public String getTmp_p() {
        return tmp_p;
    }

    public String getTmp_k() {
        return tmp_k;
    }

    public String getD204() {
        return d204;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSinonim(String sinonim) {
        this.sinonim = sinonim;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setwDissolubility(String wDissolubility) {
        this.wDissolubility = wDissolubility;
    }

    public void setvDissolubility(String vDissolubility) {
        this.vDissolubility = vDissolubility;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setTmp_p(String tmp_p) {
        this.tmp_p = tmp_p;
    }

    public void setTmp_k(String tmp_k) {
        this.tmp_k = tmp_k;
    }

    public void setD204(String d204) {
        this.d204 = d204;
    }
}

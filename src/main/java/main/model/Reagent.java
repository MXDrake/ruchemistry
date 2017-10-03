package main.model;

import javax.persistence.*;

@Entity
@Table(name="reagents")
public class Reagent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "org")
    private String org;

    @Column(name = "has_t")
    private int has_t;
    @Column(name = "has_c")
    private int has_c;
    @Column(name = "has_xn")
    private int has_xn;
    @Column(name = "has_xi")
    private int has_xi;
    @Column(name = "has_t_plus")
    private int has_t_plus;
    @Column(name = "has_e")
    private int has_e;
    @Column(name = "has_n")
    private int has_n;
    @Column(name = "has_f")
    private int has_f;
    @Column(name = "has_o")
    private int has_o;
    @Column(name = "has_f_plus")
    private int has_f_plus;

    @Column (name = "ss")
    private String ss;

    @Column (name = "rs")
    private String rs;

    @Column (name = "description")
    private String description;

    @Column (name = "formula")
    private String formula;


    @Column (name = "type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
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

    public void setId(Long id) {
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

    public int getHas_t() {
        return has_t;
    }

    public void setHas_t(int has_t) {
        this.has_t = has_t;
    }

    public int getHas_c() {
        return has_c;
    }

    public void setHas_c(int has_c) {
        this.has_c = has_c;
    }

    public int getHas_xn() {
        return has_xn;
    }

    public void setHas_xn(int has_xn) {
        this.has_xn = has_xn;
    }

    public int getHas_xi() {
        return has_xi;
    }

    public void setHas_xi(int has_xi) {
        this.has_xi = has_xi;
    }

    public int getHas_t_plus() {
        return has_t_plus;
    }

    public void setHas_t_plus(int has_t_plus) {
        this.has_t_plus = has_t_plus;
    }

    public int getHas_e() {
        return has_e;
    }

    public void setHas_e(int has_e) {
        this.has_e = has_e;
    }

    public int getHas_n() {
        return has_n;
    }

    public void setHas_n(int has_n) {
        this.has_n = has_n;
    }

    public int getHas_f() {
        return has_f;
    }

    public void setHas_f(int has_f) {
        this.has_f = has_f;
    }

    public int getHas_o() {
        return has_o;
    }

    public void setHas_o(int has_o) {
        this.has_o = has_o;
    }

    public int getHas_f_plus() {
        return has_f_plus;
    }

    public void setHas_f_plus(int has_f_plus) {
        this.has_f_plus = has_f_plus;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getDescription() {
        return description;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

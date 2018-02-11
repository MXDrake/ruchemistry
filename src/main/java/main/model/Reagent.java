package main.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Column(name = "kind")
    private String kind;

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

    @OneToMany(mappedBy = "reagentId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GoodsReagent> goodsReagent;

    public List<Goods> getGoods() {
        if (goodsReagent.size() == 0) {
            return null;
        }
        List<Goods> goodsList = new ArrayList<>();
        goodsList.addAll(goodsReagent);
        return goodsList;
    }

    public List<GoodsReagent> getGoodsReagent() {
        return goodsReagent;
    }

    public void setGoodsReagent(List<GoodsReagent> goodsReagent) {
        this.goodsReagent = goodsReagent;
    }

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reagent reagent = (Reagent) o;

        if (has_t != reagent.has_t)
            return false;
        if (has_c != reagent.has_c)
            return false;
        if (has_xn != reagent.has_xn)
            return false;
        if (has_xi != reagent.has_xi)
            return false;
        if (has_t_plus != reagent.has_t_plus)
            return false;
        if (has_e != reagent.has_e)
            return false;
        if (has_n != reagent.has_n)
            return false;
        if (has_f != reagent.has_f)
            return false;
        if (has_o != reagent.has_o)
            return false;
        if (has_f_plus != reagent.has_f_plus)
            return false;
        if (type != reagent.type)
            return false;
        if (id != null ? !id.equals(reagent.id) : reagent.id != null)
            return false;
        if (name != null ? !name.equals(reagent.name) : reagent.name != null)
            return false;
        if (sinonim != null ? !sinonim.equals(reagent.sinonim) : reagent.sinonim != null)
            return false;
        if (engName != null ? !engName.equals(reagent.engName) : reagent.engName != null)
            return false;
        if (cas != null ? !cas.equals(reagent.cas) : reagent.cas != null)
            return false;
        if (link != null ? !link.equals(reagent.link) : reagent.link != null)
            return false;
        if (image != null ? !image.equals(reagent.image) : reagent.image != null)
            return false;
        if (kind != null ? !kind.equals(reagent.kind) : reagent.kind != null)
            return false;
        if (content != null ? !content.equals(reagent.content) : reagent.content != null)
            return false;
        if (wDissolubility != null ? !wDissolubility.equals(reagent.wDissolubility) : reagent.wDissolubility != null)
            return false;
        if (vDissolubility != null ? !vDissolubility.equals(reagent.vDissolubility) : reagent.vDissolubility != null)
            return false;
        if (weight != null ? !weight.equals(reagent.weight) : reagent.weight != null)
            return false;
        if (tmp_p != null ? !tmp_p.equals(reagent.tmp_p) : reagent.tmp_p != null)
            return false;
        if (tmp_k != null ? !tmp_k.equals(reagent.tmp_k) : reagent.tmp_k != null)
            return false;
        if (d204 != null ? !d204.equals(reagent.d204) : reagent.d204 != null)
            return false;
        if (org != null ? !org.equals(reagent.org) : reagent.org != null)
            return false;
        if (ss != null ? !ss.equals(reagent.ss) : reagent.ss != null)
            return false;
        if (rs != null ? !rs.equals(reagent.rs) : reagent.rs != null)
            return false;
        if (description != null ? !description.equals(reagent.description) : reagent.description != null)
            return false;
        if (formula != null ? !formula.equals(reagent.formula) : reagent.formula != null)
            return false;
        return goodsReagent != null ? goodsReagent.equals(reagent.goodsReagent) : reagent.goodsReagent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sinonim != null ? sinonim.hashCode() : 0);
        result = 31 * result + (engName != null ? engName.hashCode() : 0);
        result = 31 * result + (cas != null ? cas.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (wDissolubility != null ? wDissolubility.hashCode() : 0);
        result = 31 * result + (vDissolubility != null ? vDissolubility.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (tmp_p != null ? tmp_p.hashCode() : 0);
        result = 31 * result + (tmp_k != null ? tmp_k.hashCode() : 0);
        result = 31 * result + (d204 != null ? d204.hashCode() : 0);
        result = 31 * result + (org != null ? org.hashCode() : 0);
        result = 31 * result + has_t;
        result = 31 * result + has_c;
        result = 31 * result + has_xn;
        result = 31 * result + has_xi;
        result = 31 * result + has_t_plus;
        result = 31 * result + has_e;
        result = 31 * result + has_n;
        result = 31 * result + has_f;
        result = 31 * result + has_o;
        result = 31 * result + has_f_plus;
        result = 31 * result + (ss != null ? ss.hashCode() : 0);
        result = 31 * result + (rs != null ? rs.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (formula != null ? formula.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (goodsReagent != null ? goodsReagent.hashCode() : 0);
        return result;
    }
}

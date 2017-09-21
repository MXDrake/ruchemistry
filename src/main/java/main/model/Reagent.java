package main.model;

import javax.persistence.*;

@Entity(name="reagents")
@Table
public class Reagent {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;


    public String getName() {
        return name;
    }
}

package ru.nnboo.NNBOO.entity;

import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long providerID;
    private String name;

    public Provider() {
    }

    public Provider(Long providerID, String name) {
        this.providerID = providerID;
        this.name = name;
    }

    public Provider(String name) {
        this.name = name;
    }

    public Long getProviderID() {
        return providerID;
    }

    public void setProviderID(Long providerID) {
        this.providerID = providerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

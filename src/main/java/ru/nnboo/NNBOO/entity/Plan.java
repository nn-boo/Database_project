package ru.nnboo.NNBOO.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planId;
    private String planName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id")
    private Provider providerId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id")
    private Worker empId;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Supply> supplies = new ArrayList<>();

    public Plan(String planName, Provider providerId, Worker empId, List<Supply> supplies) {
        this.planName = planName;
        this.providerId = providerId;
        this.empId = empId;
        this.supplies = supplies;
    }

    public Plan() {

    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Provider getVendorId() {
        return providerId;
    }

    public void setVendorId(Provider providerId) {
        this.providerId = providerId;
    }

    public Worker getEmpId() {
        return empId;
    }

    public void setEmpId(Worker empId) {
        this.empId = empId;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }
}

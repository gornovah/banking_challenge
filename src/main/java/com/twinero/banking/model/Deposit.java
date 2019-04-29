package com.twinero.banking.model;

import javax.persistence.*;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Deposit.getAll", query = "SELECT d FROM Deposit d")
})
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "dep_seq", allocationSize = 1)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private double amount;

    public Deposit() {
    }

    public Deposit(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", customer=" + customer +
                ", amount=" + amount +
                '}';
    }
}

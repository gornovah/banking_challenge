package com.twinero.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@NamedQueries(value = {
        @NamedQuery(name = "Customer.getAll", query = "SELECT c FROM Customer c")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "cus_seq", allocationSize = 1)
    private int id;
    private String user;
    private String password;


    public Customer(String user, String pass) {
        this.user = user;
        this.password = pass;
    }

    public Customer() {
    }

    public void setUser(String login) {
        this.user = login;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", login='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

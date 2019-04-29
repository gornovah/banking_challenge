package com.twinero.banking.repository;

import com.twinero.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT SUM(d) FROM Deposit d WHERE d.customer.id = :id")
    Double obtainBalance(@Param("id") Integer id);
}

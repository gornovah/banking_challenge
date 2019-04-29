package com.twinero.banking.controller;

import com.twinero.banking.model.Customer;
import com.twinero.banking.model.Deposit;
import com.twinero.banking.repository.CustomerRepository;
import com.twinero.banking.repository.DepositRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/banking")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @PostMapping("/signup")
    public Customer singUpCustomer(@Valid @RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<Deposit> makeDeposit(@PathVariable(value = "id") int id, @RequestBody Map<String, String> json) {
        Double amount = Double.valueOf(json.get("amount"));
        Optional<Customer> customer = getCustomer(id);
        if (customer.isPresent()) {
            if (amount != null || amount > 0) {
                Deposit deposit = new Deposit(customer.get(), amount);
                Deposit depositUpdate = depositRepository.save(deposit);
                return ResponseEntity.ok(depositUpdate);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<Deposit> makeWithdraw(@PathVariable(value = "id") int id, @RequestBody Map<String, String> json) {
        Double amountWithdraw = Double.valueOf(json.get("withdraw"));
        Optional<Customer> customer = getCustomer(id);
        if (customer.isPresent()) {
            if (amountWithdraw != null || amountWithdraw > 0) {
                Deposit deposit = new Deposit(customer.get(), -amountWithdraw);
                Deposit depositUpdate = depositRepository.save(deposit);
                return ResponseEntity.ok(depositUpdate);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<JSONObject> getCustomerBalance(@PathVariable(value = "id") int id) {
        Optional<Customer> customer = getCustomer(id);
        if (customer.isPresent()) {
            Double balance = customerRepository.obtainBalance(id);
            JSONObject o = new JSONObject();
            o.put("balance", balance);
            o.put("user", customer.get().getUser());
            return new ResponseEntity<>(o, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Optional<Customer> getCustomer(@PathVariable("id") int id) {
        return customerRepository.findById(id);
    }
}

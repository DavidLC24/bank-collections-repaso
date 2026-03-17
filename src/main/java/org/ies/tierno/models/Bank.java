package org.ies.tierno.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Data
public class Bank {
    private String name;
    private List<Customer> customers;
    Map<String, Account> accountByIban;

    public void addAmountInAccount (String iban, double amount){
        if (accountByIban.containsKey(iban)){
            Account account= accountByIban.get(iban);
                account.deposit(amount);
        }
//       // O también sin metodo externo con un set: //
//        if (accountByIban.containsKey(iban)){
//            Account account= accountByIban.get(iban);
//            account.setBalance(account.getBalance()+amount);
//        }
        else {
            log.info("No existe la cuenta");
        }
    }

    public Customer getCustomerByNif (String nif){
        for (Customer customer: customers){
            if (customer.getNif().equals(nif)){
                return customer;
            }
        }
        return null;
    }

    public List<Account> accountsByNif (String nif){
        Customer customer= getCustomerByNif(nif);
        List<Account> accountsCustomer= new ArrayList<>();
        if (customer!=null){
            for (Account account: accountByIban.values()){
                if (account.getNif().equals(nif)){
                    accountsCustomer.add(account);
                }
            }
        }
        return accountsCustomer;
    }

    public void withdrawAmountAccount (String iban, double amount){
        if (!accountByIban.containsKey(iban)){
            log.info("No existe esta cuenta");
        } else {
            Account account= accountByIban.get(iban);
            if (account.getBalance()<amount){
                log.info("No hay saldo suficiente");
            } else {
                account.setBalance(account.getBalance()-amount);
            }
        }
    }
}

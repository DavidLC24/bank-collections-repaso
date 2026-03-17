package org.ies.tierno.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Data
public class Bank {
    private String name;
    private List<Customer> customers;
    Map<String, Account> accountByIban;

    public void deposit (String iban, double amount){
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
                    return accountsCustomer;
                }
            }
        }
        return null;
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
//                O mejor con un metodo externo que reste el amount al balance en la propia Account:
//                account.withdraw(amount);
            }
        }
    }

    public void tranference (double amount, String origin, String destiny){
        if (accountByIban.containsKey(origin) && accountByIban.containsKey(destiny)){
            Account originAcc= accountByIban.get(origin);
            if (originAcc.getBalance()>= amount){
                Account destinyAcc= accountByIban.get(destiny);
                    originAcc.withdraw(amount);
                    destinyAcc.deposit(amount);
            } else {
                log.info("No hay saldo suficiente");
            }
        } else {
            log.info("No existe la cuenta de origen / destino");
        }
    }

    public Set<String> getZipCodeCustomerNifs (int zipCode){
        Set<String> nifs= new HashSet<>();
        for (Customer customer: customers){
            if (customer.getZipCode()==zipCode){
                nifs.add(customer.getNif());
            }
        }
        return nifs;
    }

    public List<Account> getAccountsByZipCode (int zipCode){
        Set<String> customerNifs= getZipCodeCustomerNifs(zipCode);
        List<Account> accZipCode= new ArrayList<>();
        for (Account account: accountByIban.values()){
            if (customerNifs.contains(account.getNif())){
                accZipCode.add(account);
            }
        }
        return accZipCode;
    }

}

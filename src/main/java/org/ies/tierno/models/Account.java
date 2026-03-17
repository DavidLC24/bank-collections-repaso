package org.ies.tierno.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Data
public class Account {
    private String iban;
    private String nif;
    private double balance;

    public void deposit (double amount){
        balance+=amount;
    }

    public boolean withdraw (double amount){
        if (balance>= amount){
        balance-=amount;
        return true;
        } else {
            log.info("No hay saldo suficiente");
            return false;
        }
    }

}

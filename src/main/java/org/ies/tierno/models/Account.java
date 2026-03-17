package org.ies.tierno.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Account {
    private String iban;
    private String nif;
    private double balance;

    public void deposit (double amount){
        balance+=amount;
    }


}

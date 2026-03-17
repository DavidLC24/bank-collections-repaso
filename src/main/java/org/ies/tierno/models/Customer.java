package org.ies.tierno.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Customer {
    private String nif;
    private String name;
    private String surname;
    private int zipCode;
}

package com.vaclavhnizda.groupsecretsanta.Data;

import com.orm.SugarRecord;

/**
 * Created by vaclav on 2/7/17.
 */

public class SecretSantaContact extends SugarRecord {

    String name;
    String lastName;
    String email;

    public SecretSantaContact(){}

    public SecretSantaContact(String name, String lastName, String email){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
}

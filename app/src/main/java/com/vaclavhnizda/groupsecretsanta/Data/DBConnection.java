package com.vaclavhnizda.groupsecretsanta.Data;

import java.util.List;

/**
 * Created by vaclav on 2/14/17.
 */

public class DBConnection {


    /**
     * Method will save a new entry in the apps contact list
     * @param firstName
     * @param lastName
     * @param email
     */
    public static void addContact(String firstName, String lastName, String email) {
        //TODO add check to prevent duplicates
        SecretSantaContact contact = new SecretSantaContact(firstName, lastName, email);
        contact.save();
    }

    /**
     * Method only to be used in 100% certainty it erases all db entries
     */
    public static void deleteAllContacts(){
        SecretSantaContact.deleteAll(SecretSantaContact.class);
    }

    public static List<SecretSantaContact> getAllContacts(){
        return SecretSantaContact.listAll(SecretSantaContact.class);
    }

    public static SecretSantaContact getContact(int location){
        long max = countContacts();
        if(location > max)
            return null;
        else
            return SecretSantaContact.findById(SecretSantaContact.class, location);
    }

    public static long countContacts(){
        return SecretSantaContact.count(SecretSantaContact.class);
    }

    
    public static boolean updateContact(int location,String[] info){
        //check location exists
        //check info given has 3 strings
        if(countContacts()< location || info.length < 3)
            return false;
        //update entry
        else{
            SecretSantaContact contact = getContact(location);
            contact.name = info[0];
            contact.lastName = info[1];
            contact.email = info[2];
            return true;
        }
    }

    //Testing purposes
    public static void main(String[] args){
//        DBConnection.deleteAllContacts();

        DBConnection.addContact("Paul","Rodgers","paul@gmail.com");

        DBConnection.addContact("Amy","Holand","amyCats@gmail.com");

        long count = countContacts();
        SecretSantaContact firstone = getContact(1);
        List<SecretSantaContact> secretSantaContacts = getAllContacts();
    }
}

package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Contact;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.SocialNetwork;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Telephone;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.ContactContract;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.ContactRepository;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.DataBaseHelper;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.EmailRepository;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.SocialNetworkRepository;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.TelephoneRepository;

public class ContactBussinessService {

    private ContactBussinessService() {
        super();
    }

    public static List<Contact> findAll() {
        return ContactRepository.getAll();
    }

    public static void save(Contact contact) {
        ContactRepository.save(contact);
        addLists(contact);
    }

    public static void delete(Contact contact){
        ContactRepository.delete(contact.getId());
    }

    private static void addLists(Contact contact){

        for(Telephone telephone : contact.getTelephones()){
            telephone.setContact(contact);
            TelephoneBussinessService.save(telephone);
        }

        for(Email email : contact.getEmails()){
            email.setContact(contact);
            EmailBussinessService.save(email);
        }

        for(SocialNetwork socialNetwork : contact.getSocialNetworks()){
            socialNetwork.setContact(contact);
            SocialNetworkBussinessService.save(socialNetwork);
        }

    }



    public static List<Contact> getByName(String name) {

        List<Contact> contactList = ContactRepository.getByName(name);
        return contactList;
    }

    public static Contact getAllDependencies(Contact contact){

         contact.setEmails(EmailRepository.getAllByContact(contact.getId()));
         contact.setTelephones(TelephoneRepository.getAllByContact(contact.getId()));
         contact.setSocialNetworks(SocialNetworkRepository.getAllByContact(contact.getId()));

        return contact;

    }

    public static Contact getById(Long id) {
        return ContactRepository.getById(id);
    }
}

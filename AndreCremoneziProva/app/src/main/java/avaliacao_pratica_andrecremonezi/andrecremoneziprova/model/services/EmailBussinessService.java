package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.EmailRepository;

public class EmailBussinessService {


    private EmailBussinessService() {
        super();
    }

    public static List<Email> findAll() {
        return EmailRepository.getAll();
    }

    public static void save(Email email) {
        EmailRepository.save(email);
    }

    public static void delete(Email email){
        EmailRepository.delete(email.getId());
    }

}

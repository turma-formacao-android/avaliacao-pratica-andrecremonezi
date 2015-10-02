package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Telephone;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.EmailRepository;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.TelephoneRepository;

/**
 * Created by Administrador on 02/10/2015.
 */
public class TelephoneBussinessService {

    private TelephoneBussinessService() {
        super();
    }

    public static List<Telephone> findAll() {
        return TelephoneRepository.getAll();
    }

    public static void save(Telephone telephone) {
        TelephoneRepository.save(telephone);
    }

    public static void delete(Telephone telephone){
        TelephoneRepository.delete(telephone.getId());
    }
}

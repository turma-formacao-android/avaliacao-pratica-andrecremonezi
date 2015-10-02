package avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.services;

import java.util.List;

import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.Email;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.entities.SocialNetwork;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.EmailRepository;
import avaliacao_pratica_andrecremonezi.andrecremoneziprova.model.persistence.SocialNetworkRepository;

/**
 * Created by Administrador on 02/10/2015.
 */
public class SocialNetworkBussinessService {
    private SocialNetworkBussinessService() {
        super();
    }

    public static List<SocialNetwork> findAll() {
        return SocialNetworkRepository.getAll();
    }

    public static void save(SocialNetwork socialNetwork) {
        SocialNetworkRepository.save(socialNetwork);
    }

    public static void delete(SocialNetwork socialNetwork){
        SocialNetworkRepository.delete(socialNetwork.getId());
    }
}

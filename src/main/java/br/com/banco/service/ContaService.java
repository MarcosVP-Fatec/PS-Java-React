package br.com.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.ContaEntity;
import br.com.banco.model.RespostaModel;
import br.com.banco.repository.ContaRepository;

/**
 * @author Marcos Vinicio Pereira
 * @category Service
 * @apiNote Service da entidade Conta
 */

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private RespostaModel resposta;

    //Listar todas as contas
    public Iterable<ContaEntity> listar(){
        return contaRepository.findAll();
    }

}

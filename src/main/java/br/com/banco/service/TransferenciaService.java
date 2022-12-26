package br.com.banco.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.RespostaModel;
import br.com.banco.model.TransferenciaEntity;
import br.com.banco.repository.TransferenciaRepository;

/**
 * @author Marcos Vinicio Pereira
 * @category Service
 * @apiNote Service da entidade Transferencia
 */

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private RespostaModel resposta;

    /**
     * A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
     */
    public Iterable<TransferenciaEntity> listarPorConta(Long idConta){
        return transferenciaRepository.findByConta(idConta);
    }
    
    /**
     * Caso não seja informado nenhum filtro, retornar  todos os dados de transferência. 
     */
    public Iterable<TransferenciaEntity> listar(){
        return transferenciaRepository.findAll();
    }

    /**
     * Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
     */
    public Iterable<TransferenciaEntity> listarPorPeriodo(LocalDateTime dataIni
                                                         ,LocalDateTime dataFim){
        return transferenciaRepository.findByDataTransferencia(dataIni, dataFim);
    }

    /**
     * Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.
     */
    public Iterable<TransferenciaEntity> listarPorOperador(String nomeOperador){
        return transferenciaRepository.findByNomeOperadorTransacao(nomeOperador,nomeOperador);
    }

    /**
     * Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.
     */
    public Iterable<TransferenciaEntity> listarPorPeriodoOperador(String nomeOperador
                                                                 ,LocalDateTime dataIni
                                                                 ,LocalDateTime dataFim){
        return transferenciaRepository.findByPeriodoAndNomeOperadorTransacao("TRANSFERENCIA", nomeOperador, "TRANSFERENCIA", nomeOperador, dataIni, dataFim);
    }


}

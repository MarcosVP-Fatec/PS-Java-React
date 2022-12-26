package br.com.banco.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // @Autowired
    // private RespostaModel resposta;

    private LocalDateTime formataData(String dataString){
        return LocalDateTime.parse(dataString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
     */
    private Iterable<TransferenciaEntity> listarPorConta(Long idConta){
        return transferenciaRepository.findByConta(idConta);
    }
    
    public Iterable<TransferenciaEntity> listarFiltro(Long idConta
                                                     ,String dIni
                                                     ,String dFim
                                                     ,String nomeOperadorTransacao){
        LocalDateTime dataIni = null, dataFim = null;
        if (dIni != null){
            dataIni = formataData(dIni);
            dataFim = formataData(dFim);
        }

        if (idConta != null){
            // A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
            return this.listarPorConta(idConta);

        } else if (dataIni != null && nomeOperadorTransacao == null){
            // Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
            return this.listarPorPeriodo(dataIni, dataFim);

        } else if (dataIni == null && nomeOperadorTransacao != null){
            // Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.
            return this.listarPorOperador(nomeOperadorTransacao);

        } else if(dataIni != null && nomeOperadorTransacao != null){    
            // Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.
            return this.listarPorPeriodoOperador(nomeOperadorTransacao, dataIni, dataFim);
            
        } else {
            // Caso não seja informado nenhum filtro, retornar todos os dados de transferência.
            return this.listar();
        }
    }

    /**
     * Caso não seja informado nenhum filtro, retornar  todos os dados de transferência. 
     */
    private Iterable<TransferenciaEntity> listar(){
        return transferenciaRepository.findAll();
    }

    /**
     * Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
     */
    private Iterable<TransferenciaEntity> listarPorPeriodo(LocalDateTime dataIni
                                                          ,LocalDateTime dataFim){
        return transferenciaRepository.findByDataTransferencia(dataIni, dataFim);
    }

    /**
     * Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.
     */
    private Iterable<TransferenciaEntity> listarPorOperador(String nomeOperador){
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

    /**
     * As transações devem ser exibidas junto com o saldo total (todos)
     * @return Long
     */
    public Long sumSaldo(String nomeOperadorTransacao
                        ,String dIni
                        ,String dFim){
        LocalDateTime dataIni = null, dataFim = null;

        if (dIni != null){
            dataIni = formataData(dIni);
            dataFim = formataData(dFim);
        }

        if (dataIni != null && nomeOperadorTransacao == null){
            // Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
            return 1L; //return transferenciaService.saldoTotal(dataIni, dataFim);
        } else if(dataIni != null && nomeOperadorTransacao != null){    
            // Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.
            return 3L; //transferenciaService.listarPorPeriodoOperador(nomeOperadorTransacao, dataIni, dataFim);
        } else if (dataIni == null && nomeOperadorTransacao != null){
            // Saldo Total (Operador)
            return transferenciaRepository.sumSaldoTotalOperador("TRANSFERENCIA", nomeOperadorTransacao, "TRANSFERENCIA", nomeOperadorTransacao);

        } else {
            // Saldo Total (Todos)
            return transferenciaRepository.sumSaldoTotalTodos();
        }
                    
        //return transferenciaRepository.sumSaldoTotalTodos();
    };

}

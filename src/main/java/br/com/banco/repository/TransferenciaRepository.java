package br.com.banco.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.banco.model.TransferenciaEntity;

/**
 * @author Marcos Vinicio Pereira
 * @category Repository
 * @apiNote Dao da entidade Transferencia
 */
public interface TransferenciaRepository extends CrudRepository<TransferenciaEntity, Long>{

    final String ORDEM_PADRAO = " C.idConta, T.dataTransferencia desc";

    // A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
    @Query("select T"
          +" from TransferenciaEntity T"
          +"      inner join T.conta C"
          +" where C.idConta = ?1"
          +" order by T.dataTransferencia desc")
    public Iterable<TransferenciaEntity> findByConta(Long idConta);

    // Caso não seja informado nenhum filtro, retornar  todos os dados de transferência. 
    @Query("select T"
          +" from TransferenciaEntity T"
          +"      inner join T.conta  C"
          +" order by"+ORDEM_PADRAO)
    public Iterable<TransferenciaEntity> findAll();

    // Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
    @Query("select T"
          +"  from TransferenciaEntity T"
          +"       inner join T.conta  C"
          +" where T.dataTransferencia >= ?1"
          +"   and T.dataTransferencia <= ?2"
          +"   order by"+ORDEM_PADRAO)
    public Iterable<TransferenciaEntity> findByDataTransferencia(LocalDateTime dataIni
                                                                ,LocalDateTime dataFim);

    // Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.
    @Query("select T"
          +" from TransferenciaEntity T"
          +"      inner join T.conta  C"
          +" where ( (T.nomeOperadorTransacao is not null and T.nomeOperadorTransacao = ?1) or"
          +"         (T.nomeOperadorTransacao is null and C.nomeResponsavel = ?2)"
          +"       )"
          +"   order by"+ORDEM_PADRAO)
    public Iterable<TransferenciaEntity> findByNomeOperadorTransacao(String nomeOperadorTransacao1
                                                                    ,String nomeOperadorTransacao2); 

    // Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.
    @Query("select T from TransferenciaEntity T inner join T.conta C"
          +" where ( (T.tipo  = ?1 and T.nomeOperadorTransacao = ?2) or "
          +"         (T.tipo != ?3 and C.nomeResponsavel = ?4)"
          +"       )"
          +"   and T.dataTransferencia >= ?5"
          +"   and T.dataTransferencia <= ?6"
          +" order by"+ORDEM_PADRAO)
    public Iterable<TransferenciaEntity> findByPeriodoAndNomeOperadorTransacao(String tipo1
                                                                              ,String nomeOperadorTransacao1
                                                                              ,String tipo2
                                                                              ,String nomeOperadorTransacao2
                                                                              ,LocalDateTime dataIni
                                                                              ,LocalDateTime dataFim);


    // As transações devem ser exibidas junto com o saldo total (todos)
    @Query("select sum(T.valor) from TransferenciaEntity T")
    public Long sumSaldoTotalTodos();

    // As transações devem ser exibidas junto com o saldo total (Operador)
    @Query("select sum(T.valor) from TransferenciaEntity T"
          +"       inner join T.conta  C"
          +" where ( (T.tipo  = ?1 and T.nomeOperadorTransacao = ?2) or "
          +"         (T.tipo != ?3 and C.nomeResponsavel = ?4)"
          +"       )")
    public Long sumSaldoTotalOperador(String tipo1
                                     ,String nomeOperador2
                                     ,String tipo3
                                     ,String nomeOperador4);

    // As transações devem ser exibidas junto com o saldo total (Operador)

    // As transações devem ser exibidas junto com o saldo total no período de acordo com o protótipo do documento.

}

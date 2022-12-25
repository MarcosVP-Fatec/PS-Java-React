package br.com.banco.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marcos Vinicio Pereira
 * @category Model/Entity
 * @apiNote Entidade da tabela "transferencia"
 */
@Entity
@Table(name = "transferencia")
@Getter
@Setter
public class TransferenciaEntity {

    @Id
    @Column(name = "id", insertable = true, updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "data_transferencia", nullable = false, insertable = true, updatable = true)
    private LocalDateTime dataTransferencia;

    @Column(name = "valor", nullable = false, precision = 20, scale = 2, insertable = true, updatable = true )
    private Double valor;

    @Transient
    public static final int TAMANHO_CAMPO_TRANSFERENCIA_TIPO = 15;
    @Column(name = "tipo", nullable = false, length = TAMANHO_CAMPO_TRANSFERENCIA_TIPO, insertable = true, updatable = true)
    private String tipo;

    @Transient
    public static final int TAMANHO_CAMPO_TRANSFERENCIA_NOME_OPERADOR = 50;
    @Column(name = "nome_operador_transacao", nullable = true, length = TAMANHO_CAMPO_TRANSFERENCIA_NOME_OPERADOR, insertable = true, updatable = true)    
    private String nomeOperadorTransacao;
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "conta_id", foreignKey=@ForeignKey(name="transferencia_fk1"), nullable = false, insertable = true, updatable = true )
    private ContaEntity conta;

    @Transient
    private Double saldoTotal;

    @Transient
    private Double saldoPeriodo;

}

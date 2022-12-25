package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marcos Vinicio Pereira
 * @category Model/Entity
 * @apiNote Entidade da tabela "conta"
 */
@Entity
@Table(name = "conta" , indexes = {@Index(name="conta_idx1", columnList="nome_responsavel", unique = false)})

@Getter
@Setter
public class ContaEntity {
 
    @Id
    @Column(name = "id_conta", insertable = true, updatable = false, nullable = false)
    private Long idConta;

    @Transient
    public static final int TAMANHO_CAMPO_CONTA_NOME_RESPONSAVEL = 50;

    @Column(name = "nome_responsavel", insertable = true, updatable = true, nullable = false, length = TAMANHO_CAMPO_CONTA_NOME_RESPONSAVEL)
    private String nomeResponsavel;
    
}

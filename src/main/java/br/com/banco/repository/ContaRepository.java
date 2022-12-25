package br.com.banco.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.banco.model.ContaEntity;

/**
 * @author Marcos Vinicio Pereira
 * @category Repository
 * @apiNote Dao da entidade Conta
 */

public interface ContaRepository extends CrudRepository<ContaEntity, Long>{
    
    //Listar todas as contas por ordem de Nome.
    @Query("select C from ContaEntity C order by upper(C.nomeResponsavel), id")
    public Iterable<ContaEntity> findAll();

}

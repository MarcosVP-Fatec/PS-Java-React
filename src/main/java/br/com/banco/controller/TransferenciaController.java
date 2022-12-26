package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.TransferenciaEntity;
import br.com.banco.service.TransferenciaService;

/**
 * @author Marcos Vinicio Pereira
 * @category Controller
 * @apiNote Rotas da entidade Transferencia
 */
@RestController
@RequestMapping(value = "/transferencia")
@CrossOrigin
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/listar")
    public Iterable<TransferenciaEntity> listar(@Nullable @RequestParam Long idConta
                                               ,@Nullable @RequestParam String dIni
                                               ,@Nullable @RequestParam String dFim
                                               ,@Nullable @RequestParam String nomeOperadorTransacao
                                               ){
        return transferenciaService.listarFiltro(idConta, dIni, dFim, nomeOperadorTransacao);
    }

    @GetMapping("/saldo")
    public Long saldoTotal(@Nullable @RequestParam String dIni
                          ,@Nullable @RequestParam String dFim
                          ,@Nullable @RequestParam String nomeOperadorTransacao
                          ){
        return transferenciaService.sumSaldo(nomeOperadorTransacao, dIni, dFim);                            
    }

}

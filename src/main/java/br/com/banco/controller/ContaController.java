package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.ContaEntity;
import br.com.banco.service.ContaService;

/**
 * @author Marcos Vinicio Pereira
 * @category Controller
 * @apiNote Rotas da entidade Conta 
 */
@RestController
@RequestMapping(value = "/conta")
@CrossOrigin(origins="*")
public class ContaController {
    
    @Autowired
    private ContaService contaService;

    @GetMapping("/listar")
    public Iterable<ContaEntity> listar(){
        return contaService.listar();
    }
}

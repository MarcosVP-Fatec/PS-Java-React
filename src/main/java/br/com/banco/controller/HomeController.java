package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.service.HomeService;

/**
 * @author Marcos Vinicio Pereira
 * @category Controller
 * @apiNote Rota Home - Mensagem com as rotas criadas
 */

@RestController
@RequestMapping(value = "")
@CrossOrigin(origins="*")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResponseEntity<String> home(){
        return homeService.home();
    }
    
}


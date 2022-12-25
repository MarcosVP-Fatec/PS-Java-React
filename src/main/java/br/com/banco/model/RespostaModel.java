package br.com.banco.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Marcos Vinicio Pereira
 * @category Model/Response
 * @apiNote Classe padrão de respostas
 */

@Component
@Getter
@Setter
public class RespostaModel {
    private String message;
}

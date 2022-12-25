package br.com.banco.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Marcos Vinicio Pereira
 * @category Service
 * @apiNote Service Home
 */

 @Service
 public class HomeService {

    public ResponseEntity<String> home(){
        StringBuilder body = new StringBuilder("<h2>Rotas Criadas (Exemplos)</h2></br><ul>");
        String[][] rotas = {
                             {""
                                 ,"Esta p&aacute;gina"
                                 ,""}
                            ,{"conta/listar"
                                 ,"Conta - Listar Todos"
                                 ,""}
                            ,{"transferencia/listar"
                                 ,"Transfer&ecirc;ncia - Todos "
                                 ,""}
                            ,{"transferencia/listar?idConta=1"
                                 ,"Transfer&ecirc;ncia - Por Conta (1)"
                                 ,""}
                            ,{"transferencia/listar?idConta=2"
                                 ,"Transfer&ecirc;ncia - Por Conta (2)"
                                 ,""}
                            ,{"transferencia/listar?dataIni=2019-01-01&dataFim=2020-12-31"
                                 ,"Transfer&ecirc;ncia - Por Per&iacute;odo (2019 at&eacute; 2020)"
                                 ,""}
                            ,{"transferencia/listar?nomeOperadorTransacao=Beltrano"
                                 ,"Transfer&ecirc;ncia - Por Operador (Beltrano)"
                                 ,""}
                            ,{"transferencia/listar?nomeOperadorTransacao=Ronnyscley"
                                 ,"Transfer&ecirc;ncia - Por Operador (Ronnyscley)"
                                 ,""}
                            ,{"transferencia/listar?dataIni=2019-01-01&dataFim=2020-12-31&nomeOperadorTransacao=Beltrano"
                                 ,"Transfer&ecirc;ncia - Por Per&iacute;odo|Operador (2019 at&eacute; 2020 | Beltrano)"
                                 ,""}
                           }; 
        final String pathContexto = "http://localhost:8080/banco/";
        for (String[] s : rotas) {
            body.append("<li><a href=\"");
            body.append(pathContexto);
            body.append(s[0]);
            body.append("\" target=\"_blank\">");
            body.append(s[1]);
            body.append(": \"/");
            body.append(s[2].equals("")?s[0]:s[2]);
            body.append("\"</a></li>");
        }
        body.append("</ul>");
        return new ResponseEntity<String>(body.toString(), HttpStatus.OK);
    }

}

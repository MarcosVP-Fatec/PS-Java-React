package br.com.banco.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
@CrossOrigin(origins="*")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping("/listar")
    public Iterable<TransferenciaEntity> listar(@Nullable @RequestParam Long idConta
                                               ,@Nullable @RequestParam String dIni
                                               ,@Nullable @RequestParam String dFim
                                               ,@Nullable @RequestParam String nomeOperadorTransacao
                                               ){
        LocalDateTime dataIni = null, dataFim = null;
        if (dIni != null){
            System.out.println(dIni);
            System.out.println(dFim);
            final DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dataIni = LocalDateTime.parse(dIni, dataFormat);
            dataFim = LocalDateTime.parse(dFim, dataFormat);
    }

        if (idConta != null){
            // A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária.
            return transferenciaService.listarPorConta(idConta);
        } else if (dataIni != null && nomeOperadorTransacao == null){
            // Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo.
            return transferenciaService.listarPorPeriodo(dataIni, dataFim);
           } else if (dataIni == null && nomeOperadorTransacao != null){
                // Caso seja informado o nome do operador da transação, retornar todas as transferências relacionados à aquele operador.
                return transferenciaService.listarPorOperador(nomeOperadorTransacao);
           } else if(dataIni != null && nomeOperadorTransacao != null){    
                // Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador.
                return transferenciaService.listarPorPeriodoOperador(nomeOperadorTransacao, dataIni, dataFim);
        } else {
            // Caso não seja informado nenhum filtro, retornar todos os dados de transferência.
            return transferenciaService.listar();
        }
    }

}

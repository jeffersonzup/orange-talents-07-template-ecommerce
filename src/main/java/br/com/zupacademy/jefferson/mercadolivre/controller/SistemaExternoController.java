package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.NotaFiscalRequest;
import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.RankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemaExternoController {

    @PostMapping("/notas-fiscais")
    public void criaNotaFiscal(@RequestBody @Valid NotaFiscalRequest notaFiscalRequest) throws InterruptedException{
        System.out.println("Criando nota fiscal para " + notaFiscalRequest.toString());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void rankingVendedores(@RequestBody @Valid RankingRequest rankingRequest) throws InterruptedException{
        System.out.println("Criando ranking para " + rankingRequest.toString());
        Thread.sleep(150);
    }
}

package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.PagseguroRequest;
import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.PaypalRequest;
import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.jefferson.mercadolivre.service.EventoNovaCompra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class FinalizaCompraController {
    
    private CompraRepository compraRepository;
    private EventoNovaCompra eventoNovaCompra;

    public FinalizaCompraController(CompraRepository compraRepository, EventoNovaCompra eventoNovaCompra) {
        this.compraRepository = compraRepository;
        this.eventoNovaCompra = eventoNovaCompra;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    public ResponseEntity<?> processamentoCompraPagseguro(@PathVariable("id") Long idCompra, @Valid PagseguroRequest pagseguroRequest){
        return processaCompra(idCompra, pagseguroRequest);
    }

    @PostMapping("/retorno-paypal/{id}")
    public ResponseEntity<?> processamentoCompraPaypal(@PathVariable("id") Long idCompra, @Valid PaypalRequest paypalRequest){
       return processaCompra(idCompra, paypalRequest);
    }

    private ResponseEntity<?> processaCompra(Long idCompra, RetornoGatewayPagamento retorno){
        Optional<Compra> existsIdCompra = compraRepository.findById(idCompra);
        if(existsIdCompra.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Compra compra = existsIdCompra.get();
        compra.adicionaTransacao(retorno);
        compraRepository.save(compra);

        eventoNovaCompra.processa(compra);

        return ResponseEntity.ok().build();
    }

}

package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.SolicitacaoCompraRequest;
import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import br.com.zupacademy.jefferson.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.jefferson.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.jefferson.mercadolivre.service.DisparadorDeEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class SolicitacaoCompraController {

    private ProdutoRepository produtoRepository;
    private CompraRepository compraRepository;
    private DisparadorDeEmail disparadorDeEmail;

    public SolicitacaoCompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository, DisparadorDeEmail disparadorDeEmail) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.disparadorDeEmail = disparadorDeEmail;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> solicitaCompra(@RequestBody @Valid SolicitacaoCompraRequest solicitacaoCompraRequest, @AuthenticationPrincipal Usuario usuarioLogado){
        Optional<Compra> solicitarCompra = solicitacaoCompraRequest.convertRequestToEntity(produtoRepository, usuarioLogado);
        if(solicitarCompra.isEmpty()){
            return ResponseEntity.badRequest().body("Não foi possível finalizar a compra.");
        }

        Compra compra = solicitarCompra.get();
        compraRepository.save(compra);
        disparadorDeEmail.enviarEmailCompra(compra);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(compra.getUrlPagamento())).body(compra.getUrlPagamento());
    }
}

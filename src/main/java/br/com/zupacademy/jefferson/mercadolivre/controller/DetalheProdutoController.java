package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.response.DetalheProdutoResponse;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class DetalheProdutoController {

    private ProdutoRepository produtoRepository;

    public DetalheProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable("id") Long idProduto){
        Optional<Produto> existsProduto = produtoRepository.findById(idProduto);
        if(existsProduto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Produto produto = existsProduto.get();

        return ResponseEntity.ok().body(new DetalheProdutoResponse(produto));
    }
}

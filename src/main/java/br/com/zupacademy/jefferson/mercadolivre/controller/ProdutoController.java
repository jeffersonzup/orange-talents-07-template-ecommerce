package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.ImagensRequest;
import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.ProdutoRequest;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import br.com.zupacademy.jefferson.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.jefferson.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.jefferson.mercadolivre.service.FakeUploader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    private CategoriaRepository categoriaRepository;

    private FakeUploader fakeUploader;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, FakeUploader fakeUploader) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.fakeUploader = fakeUploader;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuarioLogado){
        Produto produto = produtoRequest.convertRequestToEntity(categoriaRepository, usuarioLogado);
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.ok().body(produtoSalvo.toString());
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long idProduto, @Valid ImagensRequest imagensRequest, @AuthenticationPrincipal Usuario usuarioLogado){
        Set<String> links = fakeUploader.send(imagensRequest.getImagens());

        Optional<Produto> existsProduto = produtoRepository.findById(idProduto);
        if(existsProduto.isEmpty() || !existsProduto.get().getUsuario().equals(usuarioLogado)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        System.out.println(links);

        Produto produto = existsProduto.get();
        produto.associateImages(links);
        Produto produtoSalvo = produtoRepository.save(produto);

        return ResponseEntity.ok().body(produtoSalvo.toString());
    }

}

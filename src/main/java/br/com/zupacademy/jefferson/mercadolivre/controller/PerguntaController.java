package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.PerguntaRequest;
import br.com.zupacademy.jefferson.mercadolivre.entity.Pergunta;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import br.com.zupacademy.jefferson.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.jefferson.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.jefferson.mercadolivre.service.DisparadorDeEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping
public class PerguntaController {

    private ProdutoRepository produtoRepository;

    private PerguntaRepository perguntaRepository;

    private DisparadorDeEmail disparadorDeEmail;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, DisparadorDeEmail disparadorDeEmail) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.disparadorDeEmail = disparadorDeEmail;
    }

    @PostMapping("produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> adicionaPergunta(@PathVariable("id") Long idProduto, @RequestBody @Valid PerguntaRequest perguntaRequest, @AuthenticationPrincipal Usuario usuarioLogado){
        Optional<Produto> existsProduto = produtoRepository.findById(idProduto);
        if(existsProduto.isEmpty()){
            return ResponseEntity.badRequest().body("Não foi possível encontrar produto.");
        }

        Produto produto = existsProduto.get();
        Pergunta pergunta = perguntaRequest.convertRequestToEntity(produto, usuarioLogado);
        perguntaRepository.save(pergunta);
        disparadorDeEmail.enviarEmail(usuarioLogado, produto, pergunta);

        return ResponseEntity.ok().body("Pergunta enviada com sucesso!");
    }
}

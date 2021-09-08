package br.com.zupacademy.jefferson.mercadolivre.service;

import br.com.zupacademy.jefferson.mercadolivre.entity.Pergunta;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DisparadorDeEmail {

    public void enviarEmail(Usuario usuarioLogado, Produto produto, Pergunta pergunta) {
        System.out.println("=================================================================");
        System.out.println("Você recebeu um email do usuario: " + usuarioLogado.getUsername());
        System.out.println("Sobre o produto " + produto.getNome());
        System.out.println(pergunta.getTitulo());
    }
}

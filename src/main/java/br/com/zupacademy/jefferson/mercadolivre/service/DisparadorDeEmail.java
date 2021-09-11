package br.com.zupacademy.jefferson.mercadolivre.service;

import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
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

    public void enviarEmailCompra(Compra compra) {
        System.out.println("=================================================================");
        System.out.println("Você recebeu uma solicitação de Compra do usuário: " + compra.getUsuario().getEmail());
        System.out.println("Referente ao produto " + compra.getProduto().getNome() + " no valor de R$ " + compra.getProduto().getValor());
    }
}

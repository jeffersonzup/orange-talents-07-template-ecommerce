package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.entity.Pergunta;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @Deprecated
    public PerguntaRequest() {
    }

    public PerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta convertRequestToEntity(Produto produto, Usuario usuario) {
        return new Pergunta(titulo, produto, usuario);
    }

    public String getTitulo() {
        return titulo;
    }
}

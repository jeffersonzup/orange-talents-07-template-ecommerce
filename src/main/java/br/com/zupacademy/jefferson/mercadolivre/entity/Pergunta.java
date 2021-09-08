package br.com.zupacademy.jefferson.mercadolivre.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_perguntas_produto")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perguntas_produto")
    private Long id;

    @NotBlank
    @Column(name = "titulo_perguntas_produto", nullable = false)
    private String titulo;

    @NotNull
    @Column(name = "criacao_perguntas_produto", nullable = false)
    private LocalDateTime criadoEm;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.criadoEm = LocalDateTime.now();
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }
}

package br.com.zupacademy.jefferson.mercadolivre.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_perguntas_produto")
public class Pergunta implements Comparable<Pergunta>{

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

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.criadoEm = LocalDateTime.now();
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(id, pergunta.id) && Objects.equals(titulo, pergunta.titulo) && Objects.equals(criadoEm, pergunta.criadoEm) && Objects.equals(produto, pergunta.produto) && Objects.equals(usuario, pergunta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, criadoEm, produto, usuario);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}

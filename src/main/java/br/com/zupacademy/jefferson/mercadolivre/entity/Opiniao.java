package br.com.zupacademy.jefferson.mercadolivre.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tb_opinioes_produto")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opinoes_produto")
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "nota_opinoes_produto", nullable = false)
    private int nota;

    @NotBlank
    @Column(name = "titulo_opinoes_produto", nullable = false)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(name = "descricao_opinoes_produto", nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(int nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }

}

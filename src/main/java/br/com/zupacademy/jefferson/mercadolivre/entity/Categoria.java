package br.com.zupacademy.jefferson.mercadolivre.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @NotBlank
    @Column(name = "nome_categoria", nullable = false)
    private String nomeCategoria;

    @ManyToOne
    private Categoria superCategoria;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setSuperCategoria(Categoria superCategoria) {
        this.superCategoria = superCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", superCategoria=" + superCategoria +
                '}';
    }
}

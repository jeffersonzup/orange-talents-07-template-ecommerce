package br.com.zupacademy.jefferson.mercadolivre.entity;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_imagens_produto")
public class ImagensProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagens_produto")
    private Long id;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @URL
    @NotBlank
    @Column(name = "link_imagens_produto")
    private String link;

    @Deprecated
    public ImagensProduto() {
    }

    public ImagensProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImagensProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagensProduto that = (ImagensProduto) o;
        return Objects.equals(produto, that.produto) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }
}

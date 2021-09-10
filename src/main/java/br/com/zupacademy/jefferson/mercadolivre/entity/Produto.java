package br.com.zupacademy.jefferson.mercadolivre.entity;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.CaracteristicaRequest;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @NotBlank
    @Column(name = "nome_produto")
    private String nome;

    @NotNull
    @Positive
    @Column(name = "valor_produto")
    private BigDecimal valor;

    @Positive
    @Column(name = "quantidade_produto")
    private int quantidade;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "descricao_produto")
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @Column(name = "instante_cadastro_produto")
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagensProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, int quantidade, String descricao, Categoria categoria, Usuario usuario, Set<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas = caracteristicas.stream().map(c -> c.convert(this)).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return Collections.unmodifiableSet(caracteristicas);
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<ImagensProduto> getImagens() {
        return imagens;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", instanteCadastro=" + instanteCadastro +
                ", imagens=" + imagens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return quantidade == produto.quantidade && Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(valor, produto.valor) && Objects.equals(caracteristicas, produto.caracteristicas) && Objects.equals(descricao, produto.descricao) && Objects.equals(categoria, produto.categoria) && Objects.equals(usuario, produto.usuario) && Objects.equals(instanteCadastro, produto.instanteCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, quantidade, caracteristicas, descricao, categoria, usuario, instanteCadastro);
    }

    public void associateImages(Set<String> links) {
        Set<ImagensProduto> imagensProduto =
                links
                .stream()
                .map(link -> new ImagensProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagensProduto);
    }

    public void addOpinoes(Opiniao opiniao){
        this.opinioes.add(opiniao);
    }
}

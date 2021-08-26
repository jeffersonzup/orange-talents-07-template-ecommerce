package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.entity.Categoria;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import br.com.zupacademy.jefferson.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.jefferson.mercadolivre.validation.ExistsId;
import br.com.zupacademy.jefferson.mercadolivre.validation.UniqueCharacteristic;
import br.com.zupacademy.jefferson.mercadolivre.validation.UniqueValue;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

@UniqueCharacteristic
public class ProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Positive
    private int quantidade;

    @Size(min = 3, message = "Obrigatório no mínimo 3 caracteristicas.")
    private Set<CaracteristicaRequest> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public ProdutoRequest(String nome, BigDecimal valor, int quantidade, Set<CaracteristicaRequest> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto convertRequestToEntity(CategoriaRepository categoriaRepository, Usuario usuario) {
        @NotNull Categoria categoria = categoriaRepository.findById(categoriaId).get();
        return new Produto(nome, valor, quantidade, descricao, categoria, usuario, caracteristicas);
    }

    public boolean compareCharacteristic(){
        List<String> comparaNome = new ArrayList<>();
        for(CaracteristicaRequest caracteristica : caracteristicas){
            if(comparaNome.contains(caracteristica.getNome())){
                return true;
            }
            comparaNome.add(caracteristica.getNome());
        }
        return false;
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoriaId=" + categoriaId +
                '}';
    }
}

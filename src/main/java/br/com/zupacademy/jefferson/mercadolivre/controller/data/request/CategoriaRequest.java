package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.entity.Categoria;
import br.com.zupacademy.jefferson.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.jefferson.mercadolivre.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank(message = "Nome da categoria é obrigatório.")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nomeCategoria")
    private String nomeCategoria;

    private Long superCategoriaId;

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setSuperCategoriaId(Long superCategoriaId) {
        this.superCategoriaId = superCategoriaId;
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nomeCategoria='" + nomeCategoria + '\'' +
                ", superCategoriaId=" + superCategoriaId +
                '}';
    }

    public Categoria converterRequestToEntity(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nomeCategoria);
        if(superCategoriaId != null){
            Categoria idSuperCategoria = categoriaRepository.findById(superCategoriaId).get();
            categoria.setSuperCategoria(idSuperCategoria);
        }
        return categoria;
    }

}

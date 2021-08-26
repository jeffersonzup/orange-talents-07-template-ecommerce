package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.entity.CaracteristicaProduto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "CaracteristicaRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public CaracteristicaProduto convert(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }

}

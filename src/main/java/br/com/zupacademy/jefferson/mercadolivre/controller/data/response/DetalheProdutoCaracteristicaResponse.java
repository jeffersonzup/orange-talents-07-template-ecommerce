package br.com.zupacademy.jefferson.mercadolivre.controller.data.response;

import br.com.zupacademy.jefferson.mercadolivre.entity.CaracteristicaProduto;

public class DetalheProdutoCaracteristicaResponse {

    private final String nome;
    private final String descricao;

    public DetalheProdutoCaracteristicaResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}

package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import br.com.zupacademy.jefferson.mercadolivre.enums.GatewayPagamento;
import br.com.zupacademy.jefferson.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.jefferson.mercadolivre.validation.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class SolicitacaoCompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    @NotNull
    private GatewayPagamento gatewayPagamento;

    public SolicitacaoCompraRequest(Integer quantidade, Long produtoId, GatewayPagamento gatewayPagamento) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Optional<Compra> convertRequestToEntity(ProdutoRepository produtoRepository, Usuario usuario) {
        Optional<Produto> existsProduto = produtoRepository.findById(produtoId);

        Produto produto = existsProduto.get();

        if(produto.baixaEstoque(quantidade)){
            return Optional.of(new Compra(quantidade, produto, gatewayPagamento, usuario));
        }

        return Optional.empty();

    }
}

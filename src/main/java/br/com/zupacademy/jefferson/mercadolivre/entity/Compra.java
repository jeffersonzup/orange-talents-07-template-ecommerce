package br.com.zupacademy.jefferson.mercadolivre.entity;

import br.com.zupacademy.jefferson.mercadolivre.enums.GatewayPagamento;
import br.com.zupacademy.jefferson.mercadolivre.enums.StatusCompra;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long id;

    @NotNull
    @Positive
    @Column(name = "quantidade_compra", nullable = false)
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gateway_pagamento_compra", nullable = false)
    private GatewayPagamento gatewayPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_compra")
    private StatusCompra statusCompra;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Compra(Integer quantidade, Produto produto, GatewayPagamento gatewayPagamento, Usuario usuario) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.gatewayPagamento = gatewayPagamento;
        this.usuario = usuario;
        this.statusCompra = StatusCompra.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", gatewayPagamento=" + gatewayPagamento +
                ", statusCompra=" + statusCompra +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }

    public String getUrlPagamento() {
        return gatewayPagamento.urlRedirect(this.getId());
    }
}

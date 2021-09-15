package br.com.zupacademy.jefferson.mercadolivre.entity;

import br.com.zupacademy.jefferson.mercadolivre.controller.RetornoGatewayPagamento;
import br.com.zupacademy.jefferson.mercadolivre.enums.GatewayPagamento;
import br.com.zupacademy.jefferson.mercadolivre.enums.StatusCompra;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

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
                ", transacoes=" + transacoes +
                '}';
    }

    public String getUrlPagamento() {
        return gatewayPagamento.urlRedirect(this.getId());
    }

    public void adicionaTransacao(RetornoGatewayPagamento retornoGatewayPagamento) {
        Transacao novaTransacao = retornoGatewayPagamento.toTransacao(this);

        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transacao igual processada");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Transação já concluída com sucesso!");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes
                .stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <=1, "Tem mais de uma transacao concluida na compra " + this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

}

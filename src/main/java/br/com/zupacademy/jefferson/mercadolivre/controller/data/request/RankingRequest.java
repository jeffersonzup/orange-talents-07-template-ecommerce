package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}

package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.controller.RetornoGatewayPagamento;
import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.entity.Transacao;
import br.com.zupacademy.jefferson.mercadolivre.enums.StatusRetornoPagseguro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public PagseguroRequest(String idTransacao, StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagseguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}

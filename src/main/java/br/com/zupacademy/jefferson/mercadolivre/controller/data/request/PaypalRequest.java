package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.controller.RetornoGatewayPagamento;
import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.entity.Transacao;
import br.com.zupacademy.jefferson.mercadolivre.enums.StatusTransacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;

    @Min(0)
    @Max(1)
    private int status;

    public PaypalRequest(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusTransacao = this.status == 0 ? StatusTransacao.FALHA : StatusTransacao.SUCESSO;
        return new Transacao(statusTransacao, idTransacao, compra);
    }


}

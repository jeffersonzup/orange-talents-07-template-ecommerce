package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.entity.Transacao;

public interface RetornoGatewayPagamento {
    /**
     *
     * @param compra
     * @return nova transacao em funcao do gateway escolhido.
     */
    Transacao toTransacao(Compra compra);
}

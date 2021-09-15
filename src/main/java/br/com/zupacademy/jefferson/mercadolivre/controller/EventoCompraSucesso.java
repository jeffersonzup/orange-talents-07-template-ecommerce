package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;

/**
 * Todo evento relacionado ao sucesso de uma compra deve implmentar essa interface
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);
}

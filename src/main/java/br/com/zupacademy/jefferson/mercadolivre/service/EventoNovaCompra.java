package br.com.zupacademy.jefferson.mercadolivre.service;

import br.com.zupacademy.jefferson.mercadolivre.controller.EventoCompraSucesso;
import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import br.com.zupacademy.jefferson.mercadolivre.enums.StatusTransacao;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoNovaCompra {

    private Set<EventoCompraSucesso> eventoCompraSucesso;
    private DisparadorDeEmail disparadorDeEmail;

    public EventoNovaCompra(Set<EventoCompraSucesso> eventoCompraSucesso, DisparadorDeEmail disparadorDeEmail) {
        this.eventoCompraSucesso = eventoCompraSucesso;
        this.disparadorDeEmail = disparadorDeEmail;
    }

    public StatusTransacao processa(Compra compra) {
        if(compra.processadaComSucesso()){
            disparadorDeEmail.enviarEmailCompraSucesso(compra);
            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
            return StatusTransacao.SUCESSO;
        }else{
            disparadorDeEmail.enviarEmailCompraNegada(compra);
            return StatusTransacao.FALHA;
        }
    }
}

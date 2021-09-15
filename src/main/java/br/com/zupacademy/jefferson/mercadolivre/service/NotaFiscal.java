package br.com.zupacademy.jefferson.mercadolivre.service;

import br.com.zupacademy.jefferson.mercadolivre.controller.EventoCompraSucesso;
import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}

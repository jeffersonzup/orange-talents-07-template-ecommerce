package br.com.zupacademy.jefferson.mercadolivre.enums;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAYPAL{
        @Override
        public String urlRedirect(Long idCompra){
            String urlRetornoPayPal =
                    UriComponentsBuilder
                            .newInstance()
                            .scheme("http")
                            .host("www.paypal.com")
                            .path("/retorno-paypal/{id}")
                            .buildAndExpand(idCompra)
                            .toUriString();
            return "paypal.com?buyerId="+idCompra+"&redirectUrl="+urlRetornoPayPal;
        }
    },
    PAGSEGURO{
        @Override
        public String urlRedirect(Long idCompra){
            String urlRetornoPagseguro =
                    UriComponentsBuilder
                            .newInstance()
                            .scheme("http")
                            .host("www.pagseguro.com")
                            .path("/retorno-pagseguro/{id}")
                            .buildAndExpand(idCompra)
                            .toUriString();
            return "pagseguro.com?returnId="+idCompra+"&redirectUrl="+urlRetornoPagseguro;
        }
    };

    public abstract String urlRedirect(Long idCompra);

}

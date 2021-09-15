package br.com.zupacademy.jefferson.mercadolivre.enums;

public enum StatusRetornoPagseguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.SUCESSO;
        }
        return StatusTransacao.FALHA;
    }
}

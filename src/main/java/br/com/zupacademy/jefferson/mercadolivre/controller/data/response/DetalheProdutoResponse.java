package br.com.zupacademy.jefferson.mercadolivre.controller.data.response;

import br.com.zupacademy.jefferson.mercadolivre.entity.Pergunta;
import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class DetalheProdutoResponse {

    private final String nome;
    private final BigDecimal preco;
    private final String descricao;
    private final Set<DetalheProdutoCaracteristicaResponse> caracteristicas;
    private final Set<String> linksImagens;
    private final SortedSet<String> perguntas;
    private final Set<Map<String, String>> opinioes;
    private final int quantidadeNotas;
    private double mediaNotas;


    public DetalheProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.getCaracteristicas()
                .stream()
                .map(caracteristica ->
                        new DetalheProdutoCaracteristicaResponse(caracteristica))
                .collect(Collectors.toSet());
        this.linksImagens = produto.getImagens()
                .stream()
                .map(imagem -> imagem.getLink())
                .collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas()
                .stream()
                .map(Pergunta::getTitulo)
                .collect(Collectors.toCollection(TreeSet::new));
        this.opinioes = produto.getOpinioes()
                .stream()
                .map(opiniao -> {
                    return Map.of("Título:", opiniao.getTitulo(), "Descrição:", opiniao.getDescricao());
                }).collect(Collectors.toSet());
        List<Integer> notas = produto.getOpinioes().stream().map(opiniao -> opiniao.getNota()).collect(Collectors.toList());
        this.mediaNotas = notas.stream().mapToInt(nota -> nota).average().orElseGet(() -> 0.00);
        this.quantidadeNotas = notas.size();

//        if(calculaMedia.isPresent()){
//            this.mediaNotas = calculaMedia.getAsDouble();
//        }

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<DetalheProdutoCaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        mediaNotas = Math.round(mediaNotas * 100);
        return mediaNotas / 100;
    }

    public int getQuantidadeNotas() {
        return quantidadeNotas;
    }
}

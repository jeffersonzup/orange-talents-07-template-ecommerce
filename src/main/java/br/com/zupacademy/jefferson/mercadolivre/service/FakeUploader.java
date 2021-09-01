package br.com.zupacademy.jefferson.mercadolivre.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FakeUploader {

    public Set<String> send(List<MultipartFile> imagens){
        return imagens
                .stream()
                .map(imagem -> "https://amz.web-service.com/" + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}

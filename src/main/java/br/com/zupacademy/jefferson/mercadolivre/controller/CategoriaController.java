package br.com.zupacademy.jefferson.mercadolivre.controller;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.CategoriaRequest;
import br.com.zupacademy.jefferson.mercadolivre.entity.Categoria;
import br.com.zupacademy.jefferson.mercadolivre.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.converterRequestToEntity(categoriaRepository);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return ResponseEntity.ok().body(categoriaSalva.toString());
    }
}

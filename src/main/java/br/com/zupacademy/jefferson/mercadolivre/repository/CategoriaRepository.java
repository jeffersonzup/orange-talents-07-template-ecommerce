package br.com.zupacademy.jefferson.mercadolivre.repository;

import br.com.zupacademy.jefferson.mercadolivre.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

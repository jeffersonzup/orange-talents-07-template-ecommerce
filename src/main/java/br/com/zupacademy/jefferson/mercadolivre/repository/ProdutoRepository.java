package br.com.zupacademy.jefferson.mercadolivre.repository;

import br.com.zupacademy.jefferson.mercadolivre.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

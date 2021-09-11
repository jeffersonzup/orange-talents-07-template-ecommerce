package br.com.zupacademy.jefferson.mercadolivre.repository;

import br.com.zupacademy.jefferson.mercadolivre.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}

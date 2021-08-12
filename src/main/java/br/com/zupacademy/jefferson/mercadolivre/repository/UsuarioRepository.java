package br.com.zupacademy.jefferson.mercadolivre.repository;

import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

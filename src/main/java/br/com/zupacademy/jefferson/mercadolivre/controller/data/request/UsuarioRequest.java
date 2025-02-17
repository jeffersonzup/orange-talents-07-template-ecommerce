package br.com.zupacademy.jefferson.mercadolivre.controller.data.request;

import br.com.zupacademy.jefferson.mercadolivre.entity.Usuario;
import br.com.zupacademy.jefferson.mercadolivre.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    @Column(unique = true)
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converterRequestToEntity() {
        return new Usuario(email, senha);
    }
}

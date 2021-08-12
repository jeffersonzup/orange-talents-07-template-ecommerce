package br.com.zupacademy.jefferson.mercadolivre.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime registrado = LocalDateTime.now();

    public Usuario(String login, String senha) {
        this.email = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", registrado=" + registrado +
                '}';
    }
}

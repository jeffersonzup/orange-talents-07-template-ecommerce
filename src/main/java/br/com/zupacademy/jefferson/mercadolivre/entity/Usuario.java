package br.com.zupacademy.jefferson.mercadolivre.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotBlank
    @Email
    @Column(name = "email_usuario", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Length(min = 6)
    @Column(name = "senha_usuario", nullable = false)
    private String senha;

    @NotNull
    @PastOrPresent
    @Column(name = "registro_usuario", nullable = false)
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

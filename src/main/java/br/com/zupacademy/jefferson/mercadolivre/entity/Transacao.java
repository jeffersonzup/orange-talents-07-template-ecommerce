package br.com.zupacademy.jefferson.mercadolivre.entity;

import br.com.zupacademy.jefferson.mercadolivre.enums.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long id;

    @NotNull
    @Column(name = "status_transacao")
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    @NotBlank
    @Column(name = "id_transacao_gateway_transacao")
    private String idTransacaoGateway;

    @NotNull
    @ManyToOne
    private Compra compra;

    @NotNull
    @Column(name = "instante_transacao")
    private LocalDateTime instante;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idTransacaoGateway.equals(transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", compra=" + compra +
                ", instante=" + instante +
                '}';
    }
}

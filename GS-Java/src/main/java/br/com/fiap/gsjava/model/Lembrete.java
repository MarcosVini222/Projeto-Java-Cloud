package br.com.fiap.gsjava.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Table(name = "tb_lembrete")
@Entity
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Lembrete(Long id, String mensagem, LocalDateTime dataHora, Usuario usuario) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }
    public Lembrete() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

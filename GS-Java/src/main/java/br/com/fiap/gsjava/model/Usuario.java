package br.com.fiap.gsjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;

@Table(name = "tb_usuarios")
@Entity(name = "users")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Column(name = "senha")
    private String senha;
    @NotBlank(message = "O cpf é obrigatório")
    @Pattern(
            regexp = "\\d{11}",
            message = "O CPF deve conter exatamente 11 dígitos numéricos (somente números)"
    )
    @Column(name = "cpf")
    private String cpf;
    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome")
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UsuarioRole role;


    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "usuario")
    private List<Lembrete> lembretes;

    public Usuario(String email, String senha, String cpf, String nome, UsuarioRole role) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.role = role;
    }

    public Usuario(Long id, String email, String senha, String cpf, String nome, Localizacao localizacao, List<Lembrete> lembretes, UsuarioRole role) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.localizacao = localizacao;
        this.lembretes = lembretes;
        this.role = role;
    }
    public Usuario() {}

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<Lembrete> getLembretes() {
        return lembretes;
    }

    public void setLembretes(List<Lembrete> lembretes) {
        this.lembretes = lembretes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

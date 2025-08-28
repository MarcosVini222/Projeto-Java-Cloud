package br.com.fiap.gsjava.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "tb_localizacao")
@Entity
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @OneToMany(mappedBy = "localizacao")
    private List<Usuario> usuarios;

    public Localizacao(Long id, double latitude, double longitude, List<Usuario> usuarios) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.usuarios = usuarios;
    }

    public Localizacao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.dto;

import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain.Partida;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class JugadorDTO {
    private Long id;
    private String nombre;
    private Date fechaRegistro;
    private Float porcentajeDeExito;

    public String getNombre() {
        return nombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Float getPorcentajeDeExito() {
        return porcentajeDeExito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setPorcentajeDeExito(Float porcentajeDeExito) {
        this.porcentajeDeExito = porcentajeDeExito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

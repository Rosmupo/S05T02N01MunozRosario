package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="jugadores")
public class Jugador {

    public Jugador(){
        this.fechaRegistro = new Date();
        this.porcentajeDeExito = 0.0F;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String nombre;
    private Date fechaRegistro;
    private Float porcentajeDeExito;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Float getPorcentajeDeExito() {
        return porcentajeDeExito;
    }

    public void setId(Long id) {
        this.id = id;
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

}
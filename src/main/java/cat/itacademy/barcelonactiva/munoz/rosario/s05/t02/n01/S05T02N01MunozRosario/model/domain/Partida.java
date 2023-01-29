package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name="partidas")
public class Partida {

    public Partida (){}
    public Partida (Long jugadorId){

        setJugadorId(jugadorId);
        setDadoUno(getRandomValue());
        setDadoDos(getRandomValue());
        if(getDadoUno() + getDadoDos() == 7){
            setEsGanada(true);
        }else{
            setEsGanada(false);
        }

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private boolean esGanada;
    private int dadoUno;
    private int dadoDos;

    private Long jugadorId;

    public Long getId() {
        return id;
    }

    public boolean isEsGanada() {
        return esGanada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEsGanada(boolean esGanada) {
        this.esGanada = esGanada;
    }

    public Long getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Long jugadorId) {
        this.jugadorId = jugadorId;
    }

    public int getDadoUno() {
        return dadoUno;
    }

    public void setDadoUno(int dadoUno) {
        this.dadoUno = dadoUno;
    }

    public int getDadoDos() {
        return dadoDos;
    }

    public void setDadoDos(int dadoDos) {
        this.dadoDos = dadoDos;
    }

    private int getRandomValue(){
        int min = 1;
        int max = 6;
        int random = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return random;
    }

}

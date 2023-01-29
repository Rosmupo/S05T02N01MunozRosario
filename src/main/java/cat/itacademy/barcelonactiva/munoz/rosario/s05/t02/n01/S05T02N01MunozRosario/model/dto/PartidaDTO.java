package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.dto;

public class PartidaDTO {

    private Long id;
    private boolean esGanada;
    private int dadoUno;
    private int dadoDos;

    private Long jugadorId;

    public boolean isEsGanada() {
        return esGanada;
    }

    public Long getJugadorId() {
        return jugadorId;
    }

    public void setEsGanada(boolean esGanada) {
        this.esGanada = esGanada;
    }

    public void setJugadorId(Long jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

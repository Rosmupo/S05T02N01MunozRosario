package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.controller;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.service.JugadorService;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("players")
public class JugadorController {
    @Autowired
    public JugadorService jugadorService;
    @Autowired
    public PartidaService partidaService;

    //POST: / players:  crea un jugador/a.
    @PostMapping
    public ResponseEntity<JugadorDTO> add(@RequestBody JugadorDTO jugadorDTO){
        JugadorDTO nuevoJugador = jugadorService.add(jugadorDTO);


        if(nuevoJugador == null){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ERROR", "El jugador ya existe");
            return  new ResponseEntity<JugadorDTO>(null,responseHeaders, HttpStatus.NOT_ACCEPTABLE);

        }
        return new ResponseEntity<JugadorDTO>(nuevoJugador,null, HttpStatus.CREATED);
    }

    //PUT/ players:  modifica el  nombre  del jugador /a.
    @PutMapping
    public ResponseEntity<JugadorDTO> update(@RequestBody JugadorDTO jugadorDTO){
        return new ResponseEntity<JugadorDTO>(jugadorService.update(jugadorDTO),null, HttpStatus.OK);
    }

    //POST / players /{id}/ games / :  un jugador/a  específico realiza  un tirón  de los dados .
    @PostMapping("{id}/games")
    public ResponseEntity<PartidaDTO> addGame(@PathVariable long id) {

        PartidaDTO partidaDTO = partidaService.play(id);
        updatePorcentajeExito(id);

        if(partidaDTO == null){
            return new ResponseEntity<PartidaDTO>(null,null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PartidaDTO>(partidaDTO,null, HttpStatus.OK);
    }

    //DELETE / players /{id}/ games : elimina las  tiradas  del jugador /a.
    @DeleteMapping("{id}/games")
    public ResponseEntity<String> deleteGames(@PathVariable long id) {
        int cantidadEliminados = 0;
        cantidadEliminados = partidaService.delete(id);
        if(cantidadEliminados == -1){
            return new ResponseEntity<String>("El jugador no tiene partidas o no existe",null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Se eliminarion " + String.valueOf(cantidadEliminados) + " partidas",null, HttpStatus.OK);
    }

    //GET / players /: devuelve el  listado  de  todos los jugadores/as  del sistema  con  su  porcentaje medio de logros .
    @GetMapping
    public ResponseEntity<ArrayList<JugadorDTO>> getAll() {
        return new ResponseEntity<ArrayList<JugadorDTO>>(jugadorService.getAll(),null, HttpStatus.OK);
    }
    //GET / players /{id}/ games : devuelve el  listado  de  jugadas  por un jugador/a.
    @GetMapping("{id}/games")
    public ResponseEntity<ArrayList<PartidaDTO>> getGamesByPlayer(@PathVariable long id) {
        return new ResponseEntity<ArrayList<PartidaDTO>>(partidaService.getAllByJugadorId(id),null, HttpStatus.OK);
    }

    //GET /players/ranking:  devuelve  el ranking  medio  de todos  los  jugadores/as  del  sistema . Es decir , el   porcentaje  medio de logros .
    @GetMapping("/ranking")
    public ResponseEntity<Float> ranking() {
        return new ResponseEntity<Float>(jugadorService.getRanking(),null, HttpStatus.OK);
    }
    //GET / players /ranking/loser: devuelve al jugador/a  con peor porcentaje de éxito.
    @GetMapping("/ranking/loser")
    public ResponseEntity<JugadorDTO> loser() {
        return new ResponseEntity<JugadorDTO>(jugadorService.worstPlayer(),null, HttpStatus.OK);
    }
    //GET /players/ranking/winner:  devuelve  al  jugador con peor porcentaje de éxito.
    @GetMapping("/ranking/winner")
    public ResponseEntity<JugadorDTO> winner() {
        return new ResponseEntity<JugadorDTO>(jugadorService.bestPlayer(),null, HttpStatus.OK);
    }

    private void updatePorcentajeExito(long jugadorId ){
        JugadorDTO jugadorDTO = jugadorService.getOne(jugadorId);
        ArrayList<PartidaDTO> listPartidas = partidaService.getAllByJugadorId(jugadorId);
        int ganadas = 0;
        for(PartidaDTO item : listPartidas){
            if(item.isEsGanada()){
                ganadas++;
            }
        }
        float porcentajeExito = ganadas * 100.00F / listPartidas.size();
        jugadorDTO.setPorcentajeDeExito(porcentajeExito);
        jugadorService.updatePorcentajeExito(jugadorDTO);
    }
}

package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.service;

import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain.Jugador;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class JugadorService {
    @Autowired
    JugadorRepository jugadorRepository;

    public JugadorDTO add(JugadorDTO jugadorDTO){
        List<Jugador> list = (ArrayList<Jugador>) jugadorRepository.findAll();
        for(Jugador item : list){
            if(item.getNombre().equals(jugadorDTO.getNombre())){
                return null;
            }
        }
        Jugador jugador = dtoToJugador(jugadorDTO);

        jugador =  jugadorRepository.save(jugador);
        if(jugador.getNombre() == null){
            jugador.setNombre("ANÓNIMO " + jugador.getId());
            jugador =  jugadorRepository.save(jugador);
        }
        jugadorDTO = jugadorToDTO(jugador);

        return jugadorDTO;
    }

    public JugadorDTO update(JugadorDTO jugadorDTO){

        Jugador jugador = jugadorRepository.findById(jugadorDTO.getId()).get();
        jugador.setNombre(jugadorDTO.getNombre());
        jugador = jugadorRepository.save(jugador);

        return jugadorToDTO(jugador);
    }

    public JugadorDTO updatePorcentajeExito(JugadorDTO jugadorDTO){

        Jugador jugador = jugadorRepository.findById(jugadorDTO.getId()).get();
        jugador.setPorcentajeDeExito(jugadorDTO.getPorcentajeDeExito());
        jugador = jugadorRepository.save(jugador);

        return jugadorToDTO(jugador);
    }


    public ArrayList<JugadorDTO> getAll(){
        ArrayList<Jugador> list = (ArrayList<Jugador>) jugadorRepository.findAll();
        ArrayList<JugadorDTO> listDTO = new ArrayList<JugadorDTO>();
        for(Jugador item: list){
            listDTO.add(jugadorToDTO(item));
        }
        return listDTO;
    }

    public Float getRanking(){
        Float acum = 0F;
        Float ranking = 0F;
        ArrayList<Jugador> list = (ArrayList<Jugador>) jugadorRepository.findAll();

        for(Jugador item : list){
            if(item.getPorcentajeDeExito() != null){
                acum += item.getPorcentajeDeExito();
            }
        }

        if(list.size() != 0){
            ranking = acum / list.size();
        }

        return ranking;
    }

    public JugadorDTO worstPlayer(){
        ArrayList<Jugador> list = (ArrayList<Jugador>) jugadorRepository.findAll();
        Jugador worstPlayer = null;
        for(Jugador item : list){
            if(item.equals(list.get(0))){
                worstPlayer = item;
            }
            if(item.getPorcentajeDeExito() <= worstPlayer.getPorcentajeDeExito()){
                worstPlayer = item;
            }
        }
        JugadorDTO worstPlayerDTO = jugadorToDTO(worstPlayer);

        return worstPlayerDTO;
    }
    public JugadorDTO bestPlayer(){
        ArrayList<Jugador> list = (ArrayList<Jugador>) jugadorRepository.findAll();
        Jugador bestPlayer = null;
        for(Jugador item : list){
            if(item.equals(list.get(0))){
                bestPlayer = item;
            }

            if(item.getPorcentajeDeExito() >= bestPlayer.getPorcentajeDeExito()){
                bestPlayer = item;

            }
        }
        JugadorDTO bestPlayerDTO = jugadorToDTO(bestPlayer);

        return bestPlayerDTO;
    }

    private JugadorDTO jugadorToDTO(Jugador jugador){

        JugadorDTO jugadorDTO = new JugadorDTO();

        jugadorDTO.setId(jugador.getId());
        jugadorDTO.setNombre(jugador.getNombre());
        jugadorDTO.setFechaRegistro(jugador.getFechaRegistro());
        jugadorDTO.setPorcentajeDeExito(jugador.getPorcentajeDeExito());

        return jugadorDTO;
    }

    private Jugador dtoToJugador(JugadorDTO jugadorDTO){

        Jugador jugador = new Jugador();

        jugador.setId(jugadorDTO.getId());
        jugador.setNombre(jugadorDTO.getNombre());
        if(jugadorDTO.getFechaRegistro() != null){
            jugador.setFechaRegistro(jugadorDTO.getFechaRegistro());
        }
        if(jugadorDTO.getPorcentajeDeExito() != null){
            jugador.setPorcentajeDeExito(jugadorDTO.getPorcentajeDeExito());
        }

        return jugador;
    }

    public JugadorDTO getOne(long id) {
        return jugadorToDTO(jugadorRepository.findById(id).get());
    }
}

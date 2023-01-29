package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.service;

import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain.Jugador;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain.Partida;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.repository.PartidaRepository;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaService {
    @Autowired
    PartidaRepository partidaRepository;

    public PartidaDTO play(Long jugadorId){

        Partida partida = new Partida(jugadorId);

        partidaRepository.save(partida);

        return partidaToDTO(partida);
    }

    public int delete(Long jugadorId){
        try{
            List<Partida> list = (List<Partida>) partidaRepository.findAll();
            int count = 0;
            for(Partida item : list){
                if (item.getJugadorId() == jugadorId){
                    partidaRepository.delete(item);
                    count ++;
                }
            }
            return count;
        }catch(Exception e){
            return -1;
        }
    }

    public ArrayList<PartidaDTO> getAllByJugadorId(Long jugadorId){
        ArrayList<Partida> list = (ArrayList<Partida>) partidaRepository.findAll();
        ArrayList<PartidaDTO> listDTO = new ArrayList<PartidaDTO>();

        for(Partida item: list){
            if (item.getJugadorId() == jugadorId){
                listDTO.add(partidaToDTO(item));
            }
        }
        return listDTO;
    }

    private PartidaDTO partidaToDTO(Partida partida){

        PartidaDTO partidaDTO = new PartidaDTO();

        partidaDTO.setId(partida.getId());
        partidaDTO.setEsGanada(partida.isEsGanada());
        partidaDTO.setJugadorId(partida.getJugadorId());
        partidaDTO.setDadoUno(partida.getDadoUno());
        partidaDTO.setDadoDos(partida.getDadoDos());

        return partidaDTO;
    }

    private Partida dtoToPartida(PartidaDTO partidaDTO){

        Partida partida = new Partida();

        partida.setId(partidaDTO.getId());
        partida.setEsGanada(partidaDTO.isEsGanada());
        partida.setJugadorId(partidaDTO.getJugadorId());
        partida.setDadoUno(partidaDTO.getDadoUno());
        partida.setDadoDos(partidaDTO.getDadoDos());

        return partida;
    }

}

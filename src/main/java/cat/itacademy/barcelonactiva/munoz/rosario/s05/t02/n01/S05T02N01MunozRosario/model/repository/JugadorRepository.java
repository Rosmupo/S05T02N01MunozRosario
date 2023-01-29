package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.repository;

import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface JugadorRepository extends CrudRepository<Jugador, Long> {
}

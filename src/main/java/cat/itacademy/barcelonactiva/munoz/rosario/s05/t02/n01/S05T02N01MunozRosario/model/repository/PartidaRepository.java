package cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.repository;

import cat.itacademy.barcelonactiva.munoz.rosario.s05.t02.n01.S05T02N01MunozRosario.model.domain.Partida;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PartidaRepository extends CrudRepository<Partida, Long> {

}

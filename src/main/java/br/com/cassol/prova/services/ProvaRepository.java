package br.com.cassol.prova.services;

import br.com.cassol.prova.model.Prova;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvaRepository extends CrudRepository<Prova, Integer> {

}

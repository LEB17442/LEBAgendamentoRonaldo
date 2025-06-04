package br.unifor.fazeragendamento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.unifor.fazeragendamento.model.Agendamento;

@Repository
public interface AgendamentoRepository extends MongoRepository<Agendamento, String> { 
}

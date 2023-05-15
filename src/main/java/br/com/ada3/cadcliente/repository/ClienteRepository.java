package br.com.ada3.cadcliente.repository;


import br.com.ada3.cadcliente.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}

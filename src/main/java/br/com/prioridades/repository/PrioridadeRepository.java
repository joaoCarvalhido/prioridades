package br.com.prioridades.repository;

import br.com.prioridades.domain.Prioridade;
import br.com.prioridades.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrioridadeRepository extends JpaRepository<Prioridade, Long> {
    List<Prioridade> findByUsuario(Usuario usuario);
}

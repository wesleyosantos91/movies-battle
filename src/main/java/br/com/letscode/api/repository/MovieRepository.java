package br.com.letscode.api.repository;

import br.com.letscode.api.entity.Movie;
import br.com.letscode.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}

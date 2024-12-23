package br.com.music.app.musicapp.domain.repository;

import br.com.music.app.musicapp.domain.models.Albuns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbunsRepository extends JpaRepository<Albuns, Long> {
}

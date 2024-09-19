package br.com.music.app.musicapp.business.repository;

import br.com.music.app.musicapp.models.Albuns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbunsRepository extends JpaRepository<Albuns, Long> {
}

package br.com.music.app.musicapp.domain.repository;

import br.com.music.app.musicapp.domain.models.Albuns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbunsRepository extends JpaRepository<Albuns, Long> {

    @Query("SELECT a FROM Albuns a JOIN FETCH a.artistsByArtistsId WHERE a.artistsByArtistsId.id = :id")
    List<Albuns> findAlbunsByArtistIdWithArtistData(@Param("id") Long id);
}

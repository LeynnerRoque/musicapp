package br.com.music.app.musicapp.business.repository;

import br.com.music.app.musicapp.models.Artists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistsRepository extends JpaRepository<Artists,Long> {

    Artists findArtistsByName(String name);
}

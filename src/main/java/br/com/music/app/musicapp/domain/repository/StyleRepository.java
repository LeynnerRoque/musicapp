package br.com.music.app.musicapp.domain.repository;

import br.com.music.app.musicapp.domain.models.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Style findStyleByNameStyle(String name);
}

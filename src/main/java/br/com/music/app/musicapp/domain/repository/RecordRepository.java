package br.com.music.app.musicapp.domain.repository;

import br.com.music.app.musicapp.domain.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Record findRecordByName(String name);
}

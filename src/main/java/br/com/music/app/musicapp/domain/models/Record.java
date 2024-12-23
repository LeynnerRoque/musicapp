package br.com.music.app.musicapp.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "record", schema = "music_db", catalog = "")
@Getter
@Setter
public class Record {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @OneToMany(mappedBy = "recordByRecordId")
    private List<Artists> artistsById;

}

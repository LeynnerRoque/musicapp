package br.com.music.app.musicapp.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "artists", schema = "music_db", catalog = "")
@Getter
@Setter
public class Artists {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "type", nullable = true, length = 50)
    private String type;
    @Basic
    @Column(name = "date_create", nullable = true)
    private Date dateCreate;
    @Basic
    @Column(name = "origin", nullable = true, length = 100)
    private String origin;
    @OneToMany(mappedBy = "artistsByArtistsId")
    private Collection<Albuns> albunsById;
    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "id", nullable = false)
    private Record recordByRecordId;
}

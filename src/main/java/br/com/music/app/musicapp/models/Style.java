package br.com.music.app.musicapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "style", schema = "music_db", catalog = "")
@Getter
@Setter
public class Style {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name_style", nullable = true, length = 100)
    private String nameStyle;
    @OneToMany(mappedBy = "styleByStyleId")
    private Collection<Albuns> albunsById;
}

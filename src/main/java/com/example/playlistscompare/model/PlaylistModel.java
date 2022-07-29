package com.example.playlistscompare.model;

import javax.persistence.*;

//an entity represents a table in a relational database,
// and each entity instance corresponds to a row in that table.
@Entity
@Table(name = "playlists")
public class PlaylistModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "playlist_name")
    private String playlistName;

    public PlaylistModel() {
    }

    public PlaylistModel(String playlistName) {
        super();
        this.playlistName = playlistName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}

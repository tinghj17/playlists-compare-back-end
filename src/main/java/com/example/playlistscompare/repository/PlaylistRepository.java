package com.example.playlistscompare.repository;

import com.example.playlistscompare.model.PlaylistModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlaylistModel, Long> {

}

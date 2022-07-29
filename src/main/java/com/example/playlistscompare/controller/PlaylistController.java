package com.example.playlistscompare.controller;

import com.example.playlistscompare.model.PlaylistModel;
import com.example.playlistscompare.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;

//    GET request
    @GetMapping("playlists")
    public List<PlaylistModel> getUsers() {
//        return all data from the database
        return this.playlistRepository.findAll();
    }
}

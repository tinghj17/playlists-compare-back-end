package com.example.playlistscompare;

import com.example.playlistscompare.model.PlaylistModel;
import com.example.playlistscompare.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//http://localhost:8080/api/playlists
@SpringBootApplication
public class PlaylistsCompareApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistsCompareApplication.class, args);
	}

	@Autowired
	private PlaylistRepository playlistRepository;
	@Override
	public void run(String... args) throws Exception {
		this.playlistRepository.save((new PlaylistModel("Chill Mix")));
		this.playlistRepository.save((new PlaylistModel("Holiday Night Night")));
		this.playlistRepository.save((new PlaylistModel("Road Trip")));
		this.playlistRepository.save((new PlaylistModel("Cooking Jazz")));
		this.playlistRepository.save((new PlaylistModel("90s Road Trip")));
	}
}

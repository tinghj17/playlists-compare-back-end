package com.example.playlistscompare;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//http://localhost:8080/api/playlists-info
@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class PlaylistsCompareApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaylistsCompareApplication.class, args);
	}
}





//	@Autowired
//	private PlaylistRepository playlistRepository;
//	@Override
//	public void run(String... args) throws Exception {
//		this.playlistRepository.save((new PlaylistInfo("Chill Mix")));
//		this.playlistRepository.save((new PlaylistModel("Holiday Night Night")));
//		this.playlistRepository.save((new PlaylistModel("Road Trip")));
//		this.playlistRepository.save((new PlaylistModel("Cooking Jazz")));
//		this.playlistRepository.save((new PlaylistModel("90s Road Trip")));
//	}





//http://localhost:8080/api/playlists
//@SpringBootApplication
//public class PlaylistsCompareApplication implements CommandLineRunner {
//
//	public static void main(String[] args) {
//		SpringApplication.run(PlaylistsCompareApplication.class, args);
//	}
//
//	@Autowired
//	private PlaylistRepository playlistRepository;
//	@Override
//	public void run(String... args) throws Exception {
//		this.playlistRepository.save((new PlaylistModel("Chill Mix")));
//		this.playlistRepository.save((new PlaylistModel("Holiday Night Night")));
//		this.playlistRepository.save((new PlaylistModel("Road Trip")));
//		this.playlistRepository.save((new PlaylistModel("Cooking Jazz")));
//		this.playlistRepository.save((new PlaylistModel("90s Road Trip")));
//	}
//}

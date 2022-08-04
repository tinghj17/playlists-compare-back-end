package com.example.playlistscompare.controller;

import com.example.playlistscompare.repository.PlaylistRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PlaylistController {

    private static final String userId = "ccb58e6dmlmielpa00j321gz3";

    @Autowired
    private PlaylistRepository playlistRepository;

    //    http://localhost:8080/api/playlists-info
    @GetMapping(value = "playlists-info")
    public static ArrayList<HashMap> getPlaylistsInfo() {
        Dotenv dotenv = Dotenv.configure().filename("env").load();
        String ClientId = dotenv.get("CLIENT_ID");
        String ClientSecret = dotenv.get("CLIENT_SECRET");

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(ClientId)
                .setClientSecret(ClientSecret)
                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();

        try {
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//            System.out.println("Expires in: " + clientCredentials.getExpiresIn() + " " + spotifyApi.getAccessToken());

          GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = spotifyApi
                    .getListOfUsersPlaylists(userId)
                    .build();
            Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();

            // return Playlists as JSON
            PlaylistSimplified[] itemsInfo = playlistSimplifiedPaging.getItems();
            ArrayList<HashMap> playlistsDetails = new ArrayList<>() ;

            HashMap<String, String> infoMap;

            for (int i = 0; i < itemsInfo.length; i++) {
                    infoMap = new HashMap();
                    infoMap.put("SELECT", String.valueOf(false));
                    infoMap.put("ID", itemsInfo[i].getId());
                    infoMap.put("NAME", itemsInfo[i].getName());
                    infoMap.put("TRACKS", String.valueOf(itemsInfo[i].getTracks().getTotal()));

                playlistsDetails.add(infoMap);
            }

            return playlistsDetails;

        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
        return null;
    }

}



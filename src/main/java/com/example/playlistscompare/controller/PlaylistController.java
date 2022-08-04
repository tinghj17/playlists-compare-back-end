package com.example.playlistscompare.controller;

import com.example.playlistscompare.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PlaylistController {
    private static final String accessToken = "";
    private static final String userId = "ccb58e6dmlmielpa00j321gz3";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = spotifyApi
            .getListOfUsersPlaylists(userId)

            .build();

    @Autowired
    private PlaylistRepository playlistRepository;


    @GetMapping(value = "get-playlists")
    public static void getListOfUsersPlaylists() {
        try {
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();

            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());

        } catch (IOException | SpotifyWebApiException |
                 org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    http://localhost:8080/api/user-playlists
    @GetMapping(value = "user-playlists")
    public static ArrayList<String> getUserPlaylists() {
        try {
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();

            // return Playlists as JSON
            PlaylistSimplified[] itemsInfo = playlistSimplifiedPaging.getItems();
            ArrayList<String> playlistsDetails = new ArrayList<>();

            for (int i = 0; i < itemsInfo.length; i++) {
                playlistsDetails.add(itemsInfo[i].getName());
            }
            return playlistsDetails;

        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
        return null;
    }

    //    http://localhost:8080/api/playlists-info
    @GetMapping(value = "playlists-info")
    public static ArrayList<HashMap> getPlaylistsInfo() {
        try {
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();

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




//    @GetMapping("playlists-info")
//    public List<PlaylistInfo> getInfo() {
////        return all data from the database
//        return this.playlistRepository.findAll();
//    }


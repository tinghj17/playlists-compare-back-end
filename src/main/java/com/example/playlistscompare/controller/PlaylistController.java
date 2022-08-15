package com.example.playlistscompare.controller;

import com.example.playlistscompare.repository.PlaylistRepository;
//import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;

import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;


import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PlaylistController {

    private static final String userId = "ccb58e6dmlmielpa00j321gz3";

//    private String playlistId1 = "7iD8WHxXnGkRWaWReboqKW";
//    private String playlistId2 = "7CdVOjB4q7K2qR1VDS9Bso";


//    private ArrayList<HashMap> result1;
//    private ArrayList<HashMap> result2 = GetPlaylistsItems(playlistId2);


    @Autowired
    private PlaylistRepository playlistRepository;

    //    http://localhost:8080/api/general-info
    @GetMapping(value = "general-info")
    public static ArrayList<HashMap> getPlaylistsInfo() {
//        Dotenv dotenv = Dotenv.configure().filename("env").load();
//        String ClientId = dotenv.get("CLIENT_ID");
//        String ClientSecret = dotenv.get("CLIENT_SECRET");
        String ClientId = System.getenv("CLIENT_ID");
        String ClientSecret = System.getenv("CLIENT_SECRET");

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
            ArrayList<HashMap> playlistsDetails = new ArrayList<>();

            HashMap<String, String> infoMap;

            for (int i = 0; i < itemsInfo.length; i++) {
                infoMap = new HashMap();
                infoMap.put("SELECT", String.valueOf("false"));
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

//    http://localhost:8080/api/playlist-info


    public static ArrayList<HashMap> GetPlaylistsItems(String playlistId){
//        Dotenv dotenv = Dotenv.configure().filename("env").load();
        String ClientId = System.getenv("CLIENT_ID");
        String ClientSecret = System.getenv("CLIENT_SECRET");


        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(ClientId)
                .setClientSecret(ClientSecret)
                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();
        try {

            ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//           System.out.println("Expires in: " + clientCredentials.getExpiresIn() + " " + spotifyApi.getAccessToken());



            GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
                    .getPlaylistsItems(playlistId)
                    .build();

            Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();

            PlaylistTrack[] trackInfo = playlistTrackPaging.getItems();
            ArrayList<HashMap> trackDetails = new ArrayList<>();

            HashMap<String, String> trackMap;


            for(int i = 0; i < trackInfo.length; i++) {

                trackMap = new HashMap<>();
                trackMap.put("TRACKNAME", trackInfo[i].getTrack().getName());
                trackMap.put("ARTIST", (((Track) playlistTrackPaging.getItems()[i].getTrack()).getArtists()[0]).getName());
                trackMap.put("IMG", ((Track) playlistTrackPaging.getItems()[i].getTrack()).getAlbum().getImages()[0].getUrl());


                trackDetails.add(trackMap);

            }
//            System.out.println("Track's first artist: " + (((Track) playlistTrackPaging.getItems()[0].getTrack()).getArtists()[0]).getName());

            return trackDetails;

//            System.out.println("Total: " + playlistTrackPaging.getTotal());
//            System.out.println(playlistTrackPaging.getItems()[1].getTrack().getName());
//            System.out.println("Track's first artist: " + ((Track) playlistTrackPaging.getItems()[0].getTrack()).getArtists()[0]);

        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
        return null;
    }

//    @GetMapping(value = "compare-playlists")
//    public static void comparePlaylists(ArrayList result1, ArrayList result2) {
//
//
//
//        for(int i = 0; i < result1.size(); i++) {
//            System.out.println(result1.get(i));
//        }
//
//    }
    @GetMapping(value = "testing")
    public ArrayList<HashMap> testing(@RequestParam(name="playlistID") String playlistId1) {
        ArrayList<HashMap> result1 = GetPlaylistsItems(playlistId1);
        return result1;
    }

    @GetMapping(value = "compareResult")
    public static ArrayList<HashMap> compareResult(@RequestParam(name="playlistID1") String playlistId1, @RequestParam(name="playlistID2") String playlistId2) {
//        String playlistId1 = "3Hp089TimuOA6gyIW9dQss";
        ArrayList<HashMap> result1 = GetPlaylistsItems(playlistId1);

//        String playlistId2 = "7CdVOjB4q7K2qR1VDS9Bso";
        ArrayList<HashMap> result2 = GetPlaylistsItems(playlistId2);

        ArrayList<HashMap> same = new ArrayList<>();

//        ArrayList<HashMap> summary = new ArrayList<>(result1);
        for(int i = 0; i < result1.size(); i++) {
            for(int j = 0; j < result2.size(); j++) {
                if(result1.get(i).equals(result2.get(j))) {
                    same.add(result2.get(j));
                }
            }
        }
        ArrayList<HashMap> result = new ArrayList<>(same);
        return result;
    }


    @GetMapping(value = "compareAdvanced")
    public static ArrayList<HashMap> compareAdvanced(@RequestParam String params) {
//        String playlistId1 = "3Hp089TimuOA6gyIW9dQss";
        List<String> playlists = Arrays.asList(params.split(","));
        ArrayList<HashMap> result1 = GetPlaylistsItems(playlists.get(0));

//        String playlistId2 = "7CdVOjB4q7K2qR1VDS9Bso";
        ArrayList<HashMap> result2 = GetPlaylistsItems(playlists.get(1));

        ArrayList<HashMap> same = new ArrayList<>();

//        ArrayList<HashMap> summary = new ArrayList<>(result1);
        for(int i = 0; i < result1.size(); i++) {
            for(int j = 0; j < result2.size(); j++) {
                if(result1.get(i).equals(result2.get(j))) {
                    same.add(result2.get(j));
                }
            }
        }
        ArrayList<HashMap> result = new ArrayList<>(same);
        return result;
    }

    @GetMapping(value = "compareArtist")
    public static ArrayList<HashMap> compareArtist(@RequestParam String params) {
//        String playlistId1 = "3Hp089TimuOA6gyIW9dQss";
        List<String> playlists = Arrays.asList(params.split(","));
        ArrayList<HashMap> result1 = GetPlaylistsItems(playlists.get(0));

//        String playlistId2 = "7CdVOjB4q7K2qR1VDS9Bso";
        ArrayList<HashMap> result2 = GetPlaylistsItems(playlists.get(1));

        ArrayList<HashMap> same = new ArrayList<>();
        HashMap<String, String> artistMap;

//        ArrayList<HashMap> summary = new ArrayList<>(result1);
        for(int i = 0; i < result1.size(); i++) {
            for(int j = 0; j < result2.size(); j++) {
                if(result1.get(i).get("ARTIST").equals(result2.get(j).get("ARTIST"))) {
                    if (!same.contains(result2.get(j).get("ARTIST")))
                    {
                        artistMap = new HashMap<>();
                        artistMap.put("Artist",result2.get(j).get("ARTIST").toString());
                        same.add(artistMap);
                    }
                }
            }
        }
        ArrayList<HashMap> result = new ArrayList<HashMap>(same);
        return result;
    }

}



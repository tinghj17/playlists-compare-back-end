package com.example.playlistscompare.controller;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;

import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;


import java.io.IOException;


@RestController
@RequestMapping("api/")
public class AuthorizationController {
    Dotenv dotenv = Dotenv.configure().filename("env").load();
    String ClientId = dotenv.get("CLIENT_ID");
    String ClientSecret = dotenv.get("CLIENT_SECRET");

    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(ClientId)
            .setClientSecret(ClientSecret)
            .build();
    ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    @GetMapping(value = "get-user-code")
    public String getSpotifyUserCode(){

        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());



            return ("Expires in: " + clientCredentials.getExpiresIn() + " " + spotifyApi.getAccessToken());
        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            return ("Error: " + e.getMessage());
        }
    }
}

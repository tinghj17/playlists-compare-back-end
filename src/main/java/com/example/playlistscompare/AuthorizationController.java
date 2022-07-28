package com.example.playlistscompare;

import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class AuthorizationController {
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http:localhost:8080/api/get-user-info");
    public String code = "";

    public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId("4a1715f4d80e4461b75d1d79066ac078")
            .setClientSecret("")
            .setRedirectUri(redirectUri)
            .build();


}

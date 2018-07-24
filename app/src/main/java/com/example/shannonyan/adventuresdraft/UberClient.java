package com.example.shannonyan.adventuresdraft;

import android.content.Context;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.AccessTokenStorage;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.AccessTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.services.RidesService;

import java.util.Arrays;
import java.util.List;

public final class UberClient {

    private static UberClient uberClientInstance;
    //UBER API vars
    public String CLIENT_ID = "0toSWTHkZXJIa-llj9rh900hXrelnQeY";
    public String TOKEN = "c2hx0dzYfc5ptMEPWA0w3ODBWdUsaITDQ_UTWF4M"; //serverToken
    public String testAccessToken = "KA.eyJ2ZXJzaW9uIjoyLCJpZCI6InlWaHNQUGs3VDFxNEdienNDZHdRNmc9PSIsImV4cGlyZXNfYXQiOjE1MzQ5ODg5OTAsInBpcGVsaW5lX2tleV9pZCI6Ik1RPT0iLCJwaXBlbGluZV9pZCI6MX0.LwqUAcKulDR2oe5tKhAIBYSQpc4VwYxCWnyixNfQARE";
    public RidesService service;
    public SessionConfiguration config = new SessionConfiguration.Builder()
            // mandatory
            .setClientId(CLIENT_ID)
            // required for enhanced button features
            .setServerToken(TOKEN)
            .setScopes(Arrays.asList(Scope.PROFILE, Scope.REQUEST, Scope.HISTORY_LITE, Scope.PLACES, Scope.REQUEST_RECEIPT))
            // required for implicit grant authentication
            //.setRedirectUri("<REDIRECT_URI>")
            // optional: set sandbox as operating environment
            .setEnvironment(SessionConfiguration.Environment.SANDBOX)
            .build();

    // UberClient prevents any other class from instantiating
    private UberClient(Context context) {
        //UBER initializations
        UberSdk.initialize(config);
        AccessTokenManager accessTokenManager = new AccessTokenManager(context, testAccessToken);
        Long expirationTime = Long.valueOf(2592000);
        List<Scope> scopes = Arrays.asList(Scope.PROFILE, Scope.REQUEST, Scope.HISTORY_LITE, Scope.PLACES, Scope.REQUEST_RECEIPT);
        String refreshToken = "obtainedRefreshToken";
        String tokenType = "access_token";
        AccessToken accessToken = new AccessToken(expirationTime, scopes, testAccessToken, refreshToken, tokenType);
        AccessTokenStorage accessTokenStorage = new AccessTokenManager(context);
        accessTokenStorage.setAccessToken(accessToken);
        AccessTokenSession session = new AccessTokenSession(config, accessTokenStorage);
        service = UberRidesApi.with(session).build().createService();
    }

    // Providing Global point of access
    public static UberClient getUberClientInstance(Context context) {
        if (null == uberClientInstance) {
            uberClientInstance = new UberClient(context);
        }
        return uberClientInstance;
    }

    public void printSingleton(){
        System.out.println("Inside print uberClient");
    }

}
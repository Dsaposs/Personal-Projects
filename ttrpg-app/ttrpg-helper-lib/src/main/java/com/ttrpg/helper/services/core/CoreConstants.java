package com.ttrpg.helper.services.core;

public class CoreConstants {
    public static final String CORE_SERVICE = "http://core-service/";

    public static final String USER_ID = "{userId}";
    public static final String CHARACTER_ID = "{characterId}";
    public static final String CHARACTER_URI = "/character";
    public static final String CHARACTERS_FOR_USER_URI = "/all/" + USER_ID;
    public static final String CHARACTER_BY_ID_URI = "/all/" + USER_ID + "/" + CHARACTER_ID;
    public static final String ALL_CHARACTERS_URI = "/all";

    public static final String GAME_ID = "{gameId}";
    public static final String GAME_URI = "/game";
    public static final String GAME_BY_USER_URI = "/" + USER_ID;
}

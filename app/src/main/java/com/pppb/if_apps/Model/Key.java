package com.pppb.if_apps.Model;

public class Key {

    // Key for fragment number on fragments array
    public final static int FRAGMENT_LOGIN = 0;
    public final static int FRAGMENT_HOME = 1;
    public final static int PAGE_EXIT = -999;

    // Key for fragment listener
    public final static String CHANGE_PAGE = "CHANGE_PAGE";
    public final static String CHANGE_PAGE_NUMBER = "CHANGE_PAGE_NUMBER";

    //Key for Token
    public static String TOKEN = "";

    //Key for Base URL
    public final static String BASE_URL = "https://ifportal.labftis.net/api/v1/";

    //Key for role to access API
    public final static String ROLE_ADMIN = "admin";
    public final static String ROLE_DOSEN = "lecturer";
    public final static String ROLE_MHS = "student";
}

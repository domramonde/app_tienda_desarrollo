package com.dronesolutions.appgamarra.ui.services;

/**
 * Created by VANESSA on 28/01/2017.
 */
public class Services {

    private static final String PROTOCOL = "http://";
    //  public static final String IP_PUERTO = "192.168.2.36";
    private static final String IP_PUERTO = "192.168.1.8:3001";
    private static final String PROY = "/api/";
    private static final String SECURITY = "seguridad/";

    public static final String URL = PROTOCOL + IP_PUERTO + PROY;
    public static final String URL_SECURITY = PROTOCOL + IP_PUERTO + PROY + SECURITY;


    //Métodos
    static final String LOGIN = "login_post";
    static final String REGISTRAR_USUARIO = "usuario/registrar";


}

package ar.edu.utn.frsf.caperucita.services;

public class DataService {
    private static DataService INSTANCE = null;

    private DataService() {
    }

    public static DataService getInstance() {
        /*if(INSTANCE == null)
            INSTANCE = new DataService();*/

        return INSTANCE;
    }
}

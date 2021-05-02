package ar.edu.utn.frsf.caperucita.constants;

import ar.edu.utn.frsf.caperucita.scenary.Scenary;

public abstract class Constants {
    public static int UNKNOWN_PERCEPTION = -1;

    public static final int SCENARY_TREE = 1;
    public static final int SCENARY_CAKE = 2;
    public static final int SCENARY_WOLF = 3;
    public static final int SCENARY_FLOWER = 4;
    public static final int SCENARY_CAPERUCITA = 5;

    public static int SCENARY_WIDTH;
    public static int SCENARY_HEIGHT;
    public static int MAX_TORTAS;

    public static Double MOVEMENT_COST = 2.0;
    public static Double MOVEMENT_CAKE_COST = 10.0;
    public static Double MOVEMENT_WOLF_COST = 200.0;

    public static void setearParametros(Scenary scenary){
        int[][] forest = scenary.getForest();
        SCENARY_HEIGHT = forest.length;
        SCENARY_WIDTH = forest[0].length;
        MAX_TORTAS = scenary.getCakesTotal();
    }
}

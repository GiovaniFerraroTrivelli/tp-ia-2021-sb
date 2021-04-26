package ar.edu.utn.frsf.caperucita.scenary;

import java.awt.*;
import java.util.ArrayList;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public abstract class Scenary {
    protected int[][] forest;
    protected int[][] inicialForest;
    protected ArrayList<Point> flowersPosition;
    protected Point wolfPosition;
    protected Point caperucitaPosition;
    protected Integer cakesTotal;
    protected ArrayList<Point> wolfSpawnPoints = new ArrayList<>();

    public void initializeScenary(int[][] forest) {

        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            for (int j = 0; j < SCENARY_WIDTH; j++) {
                switch (forest[i][j]) {
                    case SCENARY_CAKE -> this.cakesTotal++;
                    case SCENARY_FLOWER -> flowersPosition.add(new Point(j, i));
                    case SCENARY_CAPERUCITA -> caperucitaPosition = new Point(j, i);
                    case SCENARY_WOLF -> wolfPosition = new Point(j, i);
                    case 0 -> {
                        if ((i < SCENARY_HEIGHT - 1 && forest[i + 1][j] == SCENARY_TREE)
                                || (j < SCENARY_WIDTH - 1 && forest[i][j + 1] == SCENARY_TREE)
                                || (i > 0 && forest[i - 1][j] == SCENARY_TREE)
                                || (j > 0 && forest[i][j - 1] == SCENARY_TREE)) {
                            wolfSpawnPoints.add(new Point(j, i));
                        }
                    }
                }
            }
        }

        this.inicialForest = new int[SCENARY_HEIGHT][SCENARY_WIDTH];
        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            this.inicialForest[i] = this.forest[i].clone();
        }
    }

    public int[][] getForest() {
        return forest;
    }

    public ArrayList<Point> getFlowersPosition() {
        return flowersPosition;
    }

    public Point getWolfPosition() {
        return wolfPosition;
    }

    public Point getCaperucitaPosition() {
        return caperucitaPosition;
    }

    public Integer getCakesTotal() {
        return cakesTotal;
    }

    public ArrayList<Point> getWolfSpawnPoints() {
        return wolfSpawnPoints;
    }

    public void setWolfSpawnPoints(ArrayList<Point> wolfSpawnPoints) {
        this.wolfSpawnPoints = wolfSpawnPoints;
    }

    public int[][] getInicialForest() {
        return inicialForest;
    }

    public void setInicialForest(int[][] inicialForest) {
        this.inicialForest = inicialForest;
    }
}
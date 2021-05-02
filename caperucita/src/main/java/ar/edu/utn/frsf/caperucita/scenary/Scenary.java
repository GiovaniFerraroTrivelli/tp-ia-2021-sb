package ar.edu.utn.frsf.caperucita.scenary;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;
import java.util.ArrayList;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public abstract class Scenary {
    @JsonIgnore
    protected int[][] forest;
    protected int[][] inicialForest;
    protected ArrayList<Point> flowersPosition;
    protected Point wolfPosition;
    protected Point caperucitaPosition;
    protected Integer cakesTotal;
    @JsonIgnore
    protected ArrayList<Point> wolfSpawnPoints = new ArrayList<>();
    protected Integer width;
    protected Integer height;

    public void initializeScenary(int[][] forest) {
        this.height = this.forest.length;
        this.width = this.forest[0].length;

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
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

        this.inicialForest = new int[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
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
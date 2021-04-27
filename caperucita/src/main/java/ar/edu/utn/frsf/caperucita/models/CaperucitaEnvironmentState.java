package ar.edu.utn.frsf.caperucita.models;

import ar.edu.utn.frsf.caperucita.scenary.Scenary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public class CaperucitaEnvironmentState extends EnvironmentState {
    @JsonIgnore
    private int[][] initialForest;
    private int[][] currentForest;
    private Point wolfPosition;
    @JsonIgnore
    private Point caperucitaPosition;
    @JsonIgnore
    private ArrayList<Point> wolfSpawnPoints;
    @JsonIgnore
    private Point wolfInitialPosition;
    @JsonIgnore
    public boolean isCaperucitaDead;

    public CaperucitaEnvironmentState() {
        currentForest = new int[SCENARY_HEIGHT][SCENARY_WIDTH];
    }

    public CaperucitaEnvironmentState(Scenary scenary) {
        currentForest = scenary.getForest();
        wolfPosition = scenary.getWolfPosition();
        caperucitaPosition = scenary.getCaperucitaPosition();
        wolfSpawnPoints = scenary.getWolfSpawnPoints();
        wolfInitialPosition = new Point(wolfPosition.x, wolfPosition.y);
        initialForest = scenary.getInicialForest();
    }

    public CaperucitaEnvironmentState duplicate() {
        CaperucitaEnvironmentState ces = new CaperucitaEnvironmentState();

        int[][] forest = new int[SCENARY_HEIGHT][SCENARY_WIDTH];
        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            forest[i] = this.currentForest[i].clone();
        }
        ces.setCurrentForest(forest);
        ces.wolfPosition = new Point(this.wolfPosition.x, this.wolfPosition.y);

        return ces;
    }

    public void setScenary(int[][] scenary) {
        this.currentForest = scenary;
    }

    public Point getWolfPosition() {
        return wolfPosition;
    }

    public void setWolfPosition(Point wolfPosition) {
        this.currentForest[this.wolfPosition.y][this.wolfPosition.x] = 0;
        this.wolfPosition = wolfPosition;
        this.currentForest[this.wolfPosition.y][this.wolfPosition.x] = SCENARY_WOLF;
    }

    public Point getCaperucitaPosition() {
        return caperucitaPosition;
    }

    public void setCaperucitaPosition(Point caperucitaPosition) {
        this.currentForest[this.caperucitaPosition.y][this.caperucitaPosition.x] = 0;
        this.caperucitaPosition = caperucitaPosition;
        this.currentForest[this.caperucitaPosition.y][this.caperucitaPosition.x] = SCENARY_CAPERUCITA;
    }

    public int[][] getCurrentForest() {
        return currentForest;
    }

    public void setCurrentForest(int[][] currentForest) {
        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            this.currentForest[i] = currentForest[i].clone();
        }
    }

    public void moveWolf() {
        this.currentForest[this.wolfPosition.y][this.wolfPosition.x] = 0;
        do {
            this.wolfPosition = this.wolfSpawnPoints.get(new Random().nextInt(this.wolfSpawnPoints.size()));
        }
        while(this.wolfPosition.equals(this.caperucitaPosition));
        this.currentForest[this.wolfPosition.y][this.wolfPosition.x] = SCENARY_WOLF;
    }

    public Point getWolfInitialPosition() {
        return wolfInitialPosition;
    }

    public void setWolfInitialPosition(Point wolfInitialPosition) {
        this.wolfInitialPosition = wolfInitialPosition;
    }

    @JsonIgnore
    public boolean isCaperucitaDead() {
        return isCaperucitaDead;
    }

    public void setCaperucitaDead(boolean caperucitaDead) {
        isCaperucitaDead = caperucitaDead;
    }

    public int[][] getInitialForest() {
        return initialForest;
    }

    public void setInitialForest(int[][] initialForest) {
        this.initialForest = initialForest;
    }

    @Override
    public void initState() {
    }

    @Override
    public String toString() {
        return "Posicion Lobo [x=" + this.wolfPosition.x + ", y=" + this.wolfPosition.y + "]\n";
    }
}

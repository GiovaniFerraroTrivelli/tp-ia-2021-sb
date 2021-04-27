package ar.edu.utn.frsf.caperucita.models;

import ar.edu.utn.frsf.caperucita.scenary.Scenary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.awt.*;
import java.util.ArrayList;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public class CaperucitaState extends SearchBasedAgentState {
    private Point posicionActual;
    private Integer vidas;
    private Integer tortas;
    @JsonIgnore
    private ArrayList<Point> flowersPositions;
    private int[][] knownScenary;
    @JsonIgnore
    private int[][] inicialKnownScenary;
    @JsonIgnore
    private Point POSICION_INICIAL;

    public CaperucitaState() {
        this.knownScenary = new int[SCENARY_HEIGHT][SCENARY_WIDTH];
    }

    public CaperucitaState(Scenary scenary) {

        this.flowersPositions = scenary.getFlowersPosition();
        this.posicionActual = scenary.getCaperucitaPosition();
        this.POSICION_INICIAL = scenary.getCaperucitaPosition();

        this.knownScenary = new int[SCENARY_HEIGHT][SCENARY_WIDTH];

        for(Point flowerPosition: flowersPositions) {
            this.knownScenary[flowerPosition.y][flowerPosition.x] = SCENARY_FLOWER;
        }

        this.knownScenary[posicionActual.y][posicionActual.x] = SCENARY_CAPERUCITA;

        this.inicialKnownScenary = this.knownScenary.clone();
        this.initState();
    }

    @Override
    public boolean equals(Object obj) {
        CaperucitaState estadoComparado = (CaperucitaState) obj;

        boolean sameScenary = true;

        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            for (int j = 0; j < SCENARY_WIDTH; j++) {
                if(knownScenary[i][j] != estadoComparado.knownScenary[i][j]) {
                    sameScenary = false;
                    break;
                }
            }

            if(!sameScenary)
                break;
        }

        boolean posicionX = estadoComparado.posicionActual.x == this.posicionActual.x;
        boolean posicionY = estadoComparado.posicionActual.y == this.posicionActual.y;
        boolean vidas = estadoComparado.vidas.equals(this.vidas);
        boolean tortas = estadoComparado.tortas.equals(this.tortas);

        return (sameScenary && posicionX && posicionY && vidas && tortas);
    }

    @Override
    public SearchBasedAgentState clone() {
        CaperucitaState caperucitaState = new CaperucitaState();
        caperucitaState.flowersPositions = this.flowersPositions;
        caperucitaState.vidas = this.vidas;
        caperucitaState.tortas = this.tortas;

        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            caperucitaState.knownScenary[i] = this.knownScenary[i].clone();
        }

        caperucitaState.posicionActual = new Point(this.posicionActual.x, this.posicionActual.y);

        return caperucitaState;
    }

    @Override
    public void updateState(Perception p) {
        CaperucitaPerception perception = (CaperucitaPerception) p;

        int[] filaActual = perception.getFilaActual();

        for (int i = this.posicionActual.x; i < SCENARY_WIDTH; i++) {
            this.knownScenary[this.posicionActual.y][i] = filaActual[i];

            if(filaActual[i] == SCENARY_TREE) {
                break;
            }
        }

        for (int i = this.posicionActual.x; i >= 0; i--) {
            this.knownScenary[this.posicionActual.y][i] = filaActual[i];

            if(filaActual[i] == SCENARY_TREE) {
                break;
            }
        }

        int[] columnaActual = perception.getColumnaActual();
        for (int i = this.posicionActual.y; i < SCENARY_HEIGHT; i++) {
            this.knownScenary[i][this.posicionActual.x] = columnaActual[i];

            if(columnaActual[i] == SCENARY_TREE) {
                break;
            }
        }

        for (int i = this.posicionActual.y; i >= 0; i--) {
            this.knownScenary[i][this.posicionActual.x] = columnaActual[i];

            if(columnaActual[i] == SCENARY_TREE) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        matrix.append("Estado Caperucita [x=" + this.posicionActual.x + ", y=" + this.posicionActual.y + "] [tortas=" + this.tortas + ", vidas=" + this.vidas + "]\n\n");

        /*for (int i = 0; i < SCENARY_HEIGHT; i++) {
            matrix.append("\n").append(this.knownScenary[i][0]);
            for (int j = 1; j < SCENARY_WIDTH; j++) {
                matrix.append(" ").append(this.knownScenary[i][j]);
            }
        }*/

        return matrix.toString();
    }

    @Override
    public void initState() {
        this.vidas = 3;
        this.tortas = 0;
    }

    public Point getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Point posicionActual) {
        this.knownScenary[this.posicionActual.y][this.posicionActual.x] = 0;
        this.posicionActual = posicionActual;
        this.knownScenary[this.posicionActual.y][this.posicionActual.x] = SCENARY_CAPERUCITA;
    }

    public Integer getVidas() {
        return vidas;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

    public Integer getTortas() {
        return tortas;
    }

    public void setTortas(Integer tortas) {
        this.tortas = tortas;
    }

    public void deathRespawn() {
        this.setPosicionActual(this.getPosicionInicial());
        this.setTortas(0);
        this.setVidas(this.vidas - 1);
    }

    @JsonIgnore
    public Point getPosicionInicial() {
        return POSICION_INICIAL;
    }

    public ArrayList<Point> getFlowersPositions() {
        return flowersPositions;
    }

    public void setFlowersPositions(ArrayList<Point> flowersPositions) {
        this.flowersPositions = flowersPositions;
    }

    public int[][] getKnownScenary() {
        return knownScenary;
    }

    public void setKnownScenary(int[][] knownScenary) {
        this.knownScenary = knownScenary;
    }

    public void updateRow(int[] row, boolean positive) {
        if(positive) {
            for (int i = this.posicionActual.x; i < SCENARY_WIDTH; i++) {
                this.knownScenary[this.posicionActual.y][i] = row[i];

                if (row[i] == SCENARY_TREE) {
                    break;
                }
            }
        }
        else {
            for (int i = this.posicionActual.x; i >= 0; i--) {
                this.knownScenary[this.posicionActual.y][i] = row[i];

                if (row[i] == SCENARY_TREE) {
                    break;
                }
            }
        }
    }

    public void updateColumn(int[] column, boolean positive) {
        if(positive) {
            for (int i = this.posicionActual.y; i < SCENARY_HEIGHT; i++) {
                this.knownScenary[i][this.posicionActual.x] = column[i];

                if(column[i] == SCENARY_TREE) {
                    break;
                }
            }
        }
        else {
            for (int i = this.posicionActual.y; i >= 0; i--) {
                this.knownScenary[i][this.posicionActual.x] = column[i];

                if (column[i] == SCENARY_TREE) {
                    break;
                }
            }
        }
    }

    @JsonIgnore
    public int[] getRow() {
        return this.knownScenary[this.posicionActual.y];
    }

    @JsonIgnore
    public int[] getColumn() {
        int[] column = new int[SCENARY_HEIGHT];
        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            column[i] = this.knownScenary[i][this.posicionActual.x];
        }

        return column;
    }

    public int[][] getInicialKnownScenary() {
        return inicialKnownScenary;
    }

    public void setInicialKnownScenary(int[][] inicialKnownScenary) {
        this.inicialKnownScenary = inicialKnownScenary;
    }

    public void removeWolfFromForest() {
        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            for (int j = 0; j < SCENARY_WIDTH; j++) {
                if(this.knownScenary[i][j] == SCENARY_WOLF) {
                    this.knownScenary[i][j] = 0;
                    return;
                }
            }
        }
    }
}

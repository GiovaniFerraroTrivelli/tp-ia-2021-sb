package ar.edu.utn.frsf.caperucita.operators;

import ar.edu.utn.frsf.caperucita.models.CaperucitaEnvironmentState;
import ar.edu.utn.frsf.caperucita.models.CaperucitaState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public class GoLeft extends SearchAction {
    private Double cost;
    private String name = "irIzquierda";

    @Override
    public CaperucitaState execute(SearchBasedAgentState s) {
        CaperucitaState caperucitaState = (CaperucitaState) s;
        Point posicionActual = caperucitaState.getPosicionActual();
        int[] filaActual = caperucitaState.getRow();

        if (filaActual[posicionActual.x - 1] == SCENARY_TREE)
            return null;

        int[] newRow = new int[SCENARY_WIDTH];
        boolean sigueRecorriendo = true;
        int distanciaRecorrida = 0;
        int cantTortas = 0;
        int lobo = 0;
        this.cost = 0.0;

        for(int i = posicionActual.x; i >= 0; i--) {
            switch(filaActual[i]) {
                case SCENARY_CAKE -> {
                    caperucitaState.setTortas(caperucitaState.getTortas() + 1);
                    cantTortas++;
                    newRow[i] = 0;
                }
                case SCENARY_TREE -> {
                    caperucitaState.setPosicionActual(new Point(i + 1, posicionActual.y));
                    sigueRecorriendo = false;
                    newRow[i] = SCENARY_TREE;
                }
                case SCENARY_FLOWER -> {
                    newRow[i] = SCENARY_FLOWER;
                    caperucitaState.setPosicionActual(new Point(i, posicionActual.y));
                    sigueRecorriendo = false;
                }
                case SCENARY_WOLF -> {
                    int vidas = caperucitaState.getVidas();
                    //if(vidas == 1) return null;

                    lobo++;

                    caperucitaState.setVidas(vidas - 1);
                    caperucitaState.setTortas(0);
                }
            }

            if (!sigueRecorriendo)
                break;
            else
                distanciaRecorrida++;
        }

        if(sigueRecorriendo) {
            caperucitaState.setPosicionActual(new Point(1, posicionActual.y));
        }

        this.cost = MOVEMENT_COST * distanciaRecorrida - cantTortas * MOVEMENT_CAKE_COST + lobo * MOVEMENT_WOLF_COST;
        this.cost = this.cost < 1.0 ? 1.0 : this.cost;

        caperucitaState.updateRow(newRow, false);
        return caperucitaState;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        CaperucitaEnvironmentState caperucitaEnvironment = (CaperucitaEnvironmentState) est;
        CaperucitaState caperucitaState = (CaperucitaState) ast;
        Point posicionActual = caperucitaState.getPosicionActual();
        int[] filaActual = caperucitaState.getRow();

        int[][] scenary = caperucitaEnvironment.getCurrentForest();

        boolean sigueRecorriendo = true;
        for(int i = posicionActual.x; i >= 0; i--) {
            switch(filaActual[i]) {
                case SCENARY_CAKE -> {
                    scenary[posicionActual.y][i] = 0;
                    caperucitaEnvironment.setScenary(scenary);
                    caperucitaState.setTortas(caperucitaState.getTortas() + 1);
                }
                case SCENARY_TREE -> {
                    Point posActual = new Point(i + 1, posicionActual.y);

                    caperucitaState.setPosicionActual(posActual);
                    caperucitaEnvironment.setCaperucitaPosition(posActual);
                    sigueRecorriendo = false;
                }
                case SCENARY_FLOWER -> {
                    Point posActual = new Point(i, posicionActual.y);

                    caperucitaState.setPosicionActual(posActual);
                    caperucitaEnvironment.setCaperucitaPosition(posActual);
                    sigueRecorriendo = false;
                }
                case SCENARY_WOLF -> {
                    int vidas = caperucitaState.getVidas();
                    caperucitaState.setVidas(vidas - 1);
                    caperucitaState.setTortas(0);

                    if(vidas == 1) {
                        caperucitaEnvironment.setCaperucitaDead(true);
                        return caperucitaEnvironment;
                    }

                    caperucitaState.setPosicionActual(caperucitaState.getPosicionInicial());
                    caperucitaState.setKnownScenary(caperucitaState.getInicialKnownScenary());
                    caperucitaEnvironment.setWolfPosition(caperucitaEnvironment.getWolfInitialPosition());
                    caperucitaEnvironment.setCaperucitaPosition(caperucitaState.getPosicionInicial());
                    caperucitaEnvironment.setScenary(caperucitaEnvironment.getInitialForest());

                    sigueRecorriendo = false;
                }
            }

            if (!sigueRecorriendo)
                break;
        }

        caperucitaEnvironment.moveWolf();
        caperucitaState.removeWolfFromForest();

        return caperucitaEnvironment;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }
}
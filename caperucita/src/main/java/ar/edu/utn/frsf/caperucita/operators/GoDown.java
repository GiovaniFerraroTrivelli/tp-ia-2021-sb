package ar.edu.utn.frsf.caperucita.operators;

import ar.edu.utn.frsf.caperucita.models.CaperucitaEnvironmentState;
import ar.edu.utn.frsf.caperucita.models.CaperucitaState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public class GoDown extends SearchAction {
    private Double cost;
    private String name = "irAbajo";

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        CaperucitaState caperucitaState = (CaperucitaState) s;
        Point posicionActual = caperucitaState.getPosicionActual();

        int[] columnaActual = caperucitaState.getColumn();

        if (columnaActual[posicionActual.y + 1] == SCENARY_TREE)
            return null;

        int[] newColumn = new int[SCENARY_HEIGHT];
        boolean sigueRecorriendo = true;
        int distanciaRecorrida = 0;
        int cantTortas = 0;
        int lobo = 0;
        this.cost = 0.0;

        for (int i = posicionActual.y; i < SCENARY_HEIGHT; i++) {
            switch (columnaActual[i]) {
                case SCENARY_CAKE -> {
                    caperucitaState.setTortas(caperucitaState.getTortas() + 1);
                    cantTortas++;
                    newColumn[i] = 0;
                }
                case SCENARY_TREE -> {
                    caperucitaState.setPosicionActual(new Point(posicionActual.x, i - 1));
                    sigueRecorriendo = false;
                    newColumn[i] = SCENARY_TREE;
                }
                case SCENARY_FLOWER -> {
                    newColumn[i] = SCENARY_FLOWER;
                    caperucitaState.setPosicionActual(new Point(posicionActual.x, i));
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
            caperucitaState.setPosicionActual(new Point(posicionActual.x, SCENARY_HEIGHT - 2));
        }

        this.cost = MOVEMENT_COST * distanciaRecorrida - cantTortas * MOVEMENT_CAKE_COST + lobo * MOVEMENT_WOLF_COST;
        this.cost = this.cost < 1.0 ? 1.0 : this.cost;

        caperucitaState.updateColumn(newColumn, true);
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
        int[] columnaActual = caperucitaState.getColumn();

        int[][] scenary = caperucitaEnvironment.getCurrentForest();

        boolean sigueRecorriendo = true;
        for (int i = posicionActual.y; i < SCENARY_HEIGHT; i++) {
            switch (columnaActual[i]) {
                case SCENARY_CAKE -> {
                    scenary[i][posicionActual.x] = 0;
                    caperucitaEnvironment.setScenary(scenary);
                    caperucitaState.setTortas(caperucitaState.getTortas() + 1);
                }
                case SCENARY_TREE -> {
                    Point posActual = new Point(posicionActual.x, i - 1);

                    caperucitaState.setPosicionActual(posActual);
                    caperucitaEnvironment.setCaperucitaPosition(posActual);
                    sigueRecorriendo = false;
                }
                case SCENARY_FLOWER -> {
                    Point posActual = new Point(posicionActual.x, i);

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
                    caperucitaEnvironment.setScenary(caperucitaEnvironment.getInitialForest());
                    caperucitaEnvironment.setWolfPosition(caperucitaEnvironment.getWolfInitialPosition());
                    caperucitaEnvironment.setCaperucitaPosition(caperucitaState.getPosicionInicial());

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

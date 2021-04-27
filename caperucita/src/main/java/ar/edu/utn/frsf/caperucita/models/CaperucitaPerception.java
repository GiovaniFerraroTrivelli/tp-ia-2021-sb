package ar.edu.utn.frsf.caperucita.models;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import static ar.edu.utn.frsf.caperucita.constants.Constants.SCENARY_HEIGHT;
import static ar.edu.utn.frsf.caperucita.constants.Constants.SCENARY_WIDTH;

public class CaperucitaPerception extends Perception {
    private int[] filaActual = new int[SCENARY_WIDTH];
    private int[] columnaActual = new int[SCENARY_HEIGHT];

    public CaperucitaPerception() {

    }

    public CaperucitaPerception(Agent agent, Environment environment) {
        super(agent, environment);
        initPerception(agent, environment);
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        CaperucitaEnvironment caperucitaEnvironment = (CaperucitaEnvironment) environment;
        int[][] scenary = caperucitaEnvironment.getEnvironmentState().getCurrentForest();
        this.filaActual = scenary[caperucitaEnvironment.getEnvironmentState().getCaperucitaPosition().y].clone();

        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            this.columnaActual[i] = scenary[i][caperucitaEnvironment.getEnvironmentState().getCaperucitaPosition().x];
        }
    }

    @Override
    public String toString() {
        return "";
    }

    public CaperucitaPerception clone() {
        CaperucitaPerception ncp = new CaperucitaPerception();

        ncp.filaActual = this.filaActual.clone();
        ncp.columnaActual = this.columnaActual.clone();

        return ncp;
    }

    public int[] getFilaActual() {
        return filaActual;
    }

    public void setFilaActual(int[] filaActual) {
        this.filaActual = filaActual;
    }

    public int[] getColumnaActual() {
        return columnaActual;
    }

    public void setColumnaActual(int[] columnaActual) {
        this.columnaActual = columnaActual;
    }
}

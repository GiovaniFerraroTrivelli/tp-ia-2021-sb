package ar.edu.utn.frsf.caperucita.models;

import ar.edu.utn.frsf.caperucita.scenary.Scenary;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import static ar.edu.utn.frsf.caperucita.constants.Constants.SCENARY_HEIGHT;
import static ar.edu.utn.frsf.caperucita.constants.Constants.SCENARY_WIDTH;

public class CaperucitaEnvironment extends Environment {

    public CaperucitaEnvironment(Scenary scenary) {
        this.environmentState = new CaperucitaEnvironmentState(scenary);
    }

    public CaperucitaEnvironmentState getEnvironmentState() {
        return (CaperucitaEnvironmentState) this.environmentState;
    }

    @Override
    public Perception getPercept() {
        CaperucitaPerception cp = new CaperucitaPerception();
        CaperucitaEnvironmentState ces = (CaperucitaEnvironmentState) this.environmentState;

        int[][] cf = ces.getCurrentForest();

        int[] fila = new int[SCENARY_WIDTH];
        fila = cf[ces.getCaperucitaPosition().y].clone();
        cp.setFilaActual(fila);

        int[] columna = new int[SCENARY_HEIGHT];
        for (int i = 0; i < SCENARY_HEIGHT; i++) {
            columna[i] = cf[i][ces.getCaperucitaPosition().x];
        }
        cp.setColumnaActual(columna);

        return cp;
    }

    public String toString() {
        return environmentState.toString();
    }

    public boolean agentFailed(Action actionReturned) {
        return ((CaperucitaEnvironmentState)this.environmentState).isCaperucitaDead();
    }
}

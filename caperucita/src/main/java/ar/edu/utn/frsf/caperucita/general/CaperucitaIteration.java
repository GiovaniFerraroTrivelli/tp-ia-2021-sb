package ar.edu.utn.frsf.caperucita.general;

import ar.edu.utn.frsf.caperucita.models.CaperucitaState;
import frsf.cidisi.faia.agent.Action;

public class CaperucitaIteration {
    private CaperucitaState caperucitaState;
    private Action action;

    public CaperucitaIteration(CaperucitaState caperucitaState, Action action) {
        this.caperucitaState = caperucitaState;
        this.action = action;
    }

    public CaperucitaState getCaperucitaState() {
        return caperucitaState;
    }

    public void setCaperucitaState(CaperucitaState caperucitaState) {
        this.caperucitaState = caperucitaState;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}

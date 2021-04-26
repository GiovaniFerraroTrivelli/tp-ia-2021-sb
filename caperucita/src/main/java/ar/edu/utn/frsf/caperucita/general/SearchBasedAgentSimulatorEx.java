package ar.edu.utn.frsf.caperucita.general;

import ar.edu.utn.frsf.caperucita.models.Caperucita;
import ar.edu.utn.frsf.caperucita.models.CaperucitaState;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class SearchBasedAgentSimulatorEx extends SearchBasedAgentSimulator {
    private ModelRun modelRun;

    public SearchBasedAgentSimulatorEx(Environment environment, Agent agent, ModelRun modelRun) {
        super(environment, agent);
        this.modelRun = modelRun;
    }

    @Override
    public void actionReturned(Agent agent, Action action) {
        this.showSolution();
        Caperucita caperucita = (Caperucita) agent;
        CaperucitaState caperucitaState = (CaperucitaState) ((CaperucitaState)caperucita.getAgentState()).clone();

        this.updateState(action);

        modelRun.addIteration(new CaperucitaIteration(caperucitaState, action));
    }

    void showSolution() {
        GoalBasedAgent agent = (GoalBasedAgent) this.getAgents().firstElement();

        agent.getSolver().showSolution();
    }
}

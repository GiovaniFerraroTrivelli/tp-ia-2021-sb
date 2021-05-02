package ar.edu.utn.frsf.caperucita.general;

import ar.edu.utn.frsf.caperucita.models.Caperucita;
import ar.edu.utn.frsf.caperucita.models.CaperucitaEnvironmentState;
import ar.edu.utn.frsf.caperucita.models.CaperucitaGoal;
import ar.edu.utn.frsf.caperucita.models.CaperucitaState;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

import static ar.edu.utn.frsf.caperucita.constants.Constants.SCENARY_HEIGHT;
import static ar.edu.utn.frsf.caperucita.constants.Constants.SCENARY_WIDTH;

public class SearchBasedAgentSimulatorEx extends SearchBasedAgentSimulator {
    private ModelRun modelRun;

    public SearchBasedAgentSimulatorEx(Environment environment, Agent agent, ModelRun modelRun) {
        super(environment, agent);
        this.modelRun = modelRun;
    }

    @Override
    public void start(){
        super.start();
        Caperucita caperucita = (Caperucita) this.getAgents().firstElement();

        CaperucitaState caperucitaState = (CaperucitaState) ((CaperucitaState)caperucita.getAgentState()).clone();
        CaperucitaEnvironmentState caperucitaEnvironmentState = ((CaperucitaEnvironmentState)this.environment.getEnvironmentState()).duplicate();
        if((new CaperucitaGoal()).isGoalState(caperucitaState)) {
            modelRun.setSuccess(true);
            modelRun.addScenaryIteration(caperucitaEnvironmentState);
            modelRun.addCaperucitaIteration(new CaperucitaIteration(caperucitaState, null));
        }
    }

    @Override
    public void actionReturned(Agent agent, Action action) {
        this.showSolution();
        Caperucita caperucita = (Caperucita) agent;
        CaperucitaState caperucitaState = (CaperucitaState) ((CaperucitaState)caperucita.getAgentState()).clone();
        CaperucitaEnvironmentState caperucitaEnvironmentState = ((CaperucitaEnvironmentState)this.environment.getEnvironmentState()).duplicate();

        modelRun.addScenaryIteration(caperucitaEnvironmentState);

        this.updateState(action);

        modelRun.addCaperucitaIteration(new CaperucitaIteration(caperucitaState, action));
    }

    void showSolution() {
        GoalBasedAgent agent = (GoalBasedAgent) this.getAgents().firstElement();

        agent.getSolver().showSolution();
    }
}

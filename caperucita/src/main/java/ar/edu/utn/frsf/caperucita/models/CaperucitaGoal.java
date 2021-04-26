package ar.edu.utn.frsf.caperucita.models;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class CaperucitaGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        //System.out.println(agentState);
        CaperucitaState caperucitaState = (CaperucitaState) agentState;
        //
        return (/*caperucitaState.getTortas() == 2 && */caperucitaState.getFlowersPositions().stream()
                .anyMatch(point ->
                        point.x == caperucitaState.getPosicionActual().x &&
                                point.y == caperucitaState.getPosicionActual().y)
        );
    }
}

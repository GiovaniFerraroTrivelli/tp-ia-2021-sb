package ar.edu.utn.frsf.caperucita.models;

import ar.edu.utn.frsf.caperucita.functions.CostFunction;
import ar.edu.utn.frsf.caperucita.functions.Heuristic;
import ar.edu.utn.frsf.caperucita.operators.GoDown;
import ar.edu.utn.frsf.caperucita.operators.GoLeft;
import ar.edu.utn.frsf.caperucita.operators.GoRight;
import ar.edu.utn.frsf.caperucita.operators.GoUp;
import ar.edu.utn.frsf.caperucita.scenary.Scenary;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Caperucita extends SearchBasedAgent {

    public Caperucita(Scenary scenary) {
        CaperucitaGoal caperucitaGoal = new CaperucitaGoal();

        CaperucitaState caperucitaState = new CaperucitaState(scenary);
        this.setAgentState(caperucitaState);

        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.add(new GoUp());
        operators.add(new GoLeft());
        operators.add(new GoRight());
        operators.add(new GoDown());

        Problem problem = new Problem(caperucitaGoal, caperucitaState, operators);
        this.setProblem(problem);
    }

    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);

    }

    @Override
    public Action selectAction() {
        //BreathFirstSearch strategy = new BreathFirstSearch();
        AStarSearch strategy = new AStarSearch(new CostFunction(), new Heuristic());
        //UniformCostSearch strategy = new UniformCostSearch(new CostFunction());

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.PDF_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;

        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(Caperucita.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

}
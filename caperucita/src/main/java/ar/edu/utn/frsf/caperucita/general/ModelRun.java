package ar.edu.utn.frsf.caperucita.general;

import ar.edu.utn.frsf.caperucita.models.CaperucitaEnvironmentState;
import ar.edu.utn.frsf.caperucita.scenary.Scenary;

import java.util.ArrayList;

public class ModelRun {
    private Scenary scenary;
    private ArrayList<CaperucitaIteration> caperucitaIterations;
    private ArrayList<CaperucitaEnvironmentState> environmentIterations;

    public ModelRun() {
        this.caperucitaIterations = new ArrayList<>();
        this.environmentIterations = new ArrayList<>();
    }

    public Scenary getScenary() {
        return scenary;
    }

    public void setScenary(Scenary scenary) {
        this.scenary = scenary;
    }

    public ArrayList<CaperucitaIteration> getCaperucitaIterations() {
        return caperucitaIterations;
    }

    public void setCaperucitaIterations(ArrayList<CaperucitaIteration> caperucitaIterations) {
        this.caperucitaIterations = caperucitaIterations;
    }

    public ArrayList<CaperucitaEnvironmentState> getEnvironmentIterations() {
        return environmentIterations;
    }

    public void setEnvironmentIterations(ArrayList<CaperucitaEnvironmentState> environmentIterations) {
        this.environmentIterations = environmentIterations;
    }

    public void addCaperucitaIteration(CaperucitaIteration caperucitaIteration) {
        this.caperucitaIterations.add(caperucitaIteration);
    }

    public void addScenaryIteration(CaperucitaEnvironmentState caperucitaEnvironmentState) {
        this.environmentIterations.add(caperucitaEnvironmentState);
    }
}

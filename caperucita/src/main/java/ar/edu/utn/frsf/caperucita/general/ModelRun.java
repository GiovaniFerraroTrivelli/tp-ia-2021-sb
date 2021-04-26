package ar.edu.utn.frsf.caperucita.general;

import ar.edu.utn.frsf.caperucita.scenary.Scenary;

import java.util.ArrayList;

public class ModelRun {
    private Scenary scenary;
    private ArrayList<CaperucitaIteration> caperucitaIterations;

    public ModelRun() {
        this.caperucitaIterations = new ArrayList<>();
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

    public void addIteration(CaperucitaIteration caperucitaIteration) {
        this.caperucitaIterations.add(caperucitaIteration);
    }
}

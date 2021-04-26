package ar.edu.utn.frsf.caperucita.scenary;

import java.util.ArrayList;

import static ar.edu.utn.frsf.caperucita.constants.Constants.*;

public class Scenary1 extends Scenary {

    public Scenary1() {
        this.flowersPosition = new ArrayList<>();
        this.cakesTotal = 0;

        this.forest = new int[][]{
                {SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_CAKE, SCENARY_FLOWER, SCENARY_FLOWER, SCENARY_TREE, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, SCENARY_CAKE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, SCENARY_TREE, 0, 0, 0, 0, 0, SCENARY_CAKE, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_WOLF, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, SCENARY_CAKE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, SCENARY_CAPERUCITA, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_CAKE, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_CAKE, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, SCENARY_TREE},
                {SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE, SCENARY_TREE},
        };

        this.initializeScenary(forest);
    }
}
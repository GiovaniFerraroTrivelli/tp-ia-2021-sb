package ar.edu.utn.frsf.caperucita.controllers;

import ar.edu.utn.frsf.caperucita.constants.Constants;
import ar.edu.utn.frsf.caperucita.general.ModelRun;
import ar.edu.utn.frsf.caperucita.general.RunData;
import ar.edu.utn.frsf.caperucita.general.SearchBasedAgentSimulatorEx;
import ar.edu.utn.frsf.caperucita.models.Caperucita;
import ar.edu.utn.frsf.caperucita.models.CaperucitaEnvironment;
import ar.edu.utn.frsf.caperucita.scenary.*;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = { "http://localhost:4200", "http://caperucita.shiobi.me" })
public class WebController {

    @PostMapping("/run")
    public ResponseEntity<ModelRun> run(@RequestBody RunData runData)
    {
        ModelRun modelRun = new ModelRun();

        Scenary scenary;

        if(runData.getScenary() != null) {
            switch (runData.getScenary()) {
                case 2 -> scenary = new Scenary2();
                case 3 -> scenary = new Scenary3();
                case 4 -> scenary = new Scenary4();
                case 5 -> scenary = new Scenary5();
                default -> scenary = new Scenary1();
            }
        }
        else {
            scenary = new Scenary4();
        }

        Constants.setearParametros(scenary);

        modelRun.setScenary(scenary);

        Caperucita agent = new Caperucita(scenary, runData.getStrategy());
        CaperucitaEnvironment environment = new CaperucitaEnvironment(scenary);

        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulatorEx(environment, agent, modelRun);

        simulator.start();

        return ResponseEntity.ok(modelRun);
    }
}

package ar.edu.utn.frsf.caperucita.controllers;

import ar.edu.utn.frsf.caperucita.general.ModelRun;
import ar.edu.utn.frsf.caperucita.general.SearchBasedAgentSimulatorEx;
import ar.edu.utn.frsf.caperucita.models.Caperucita;
import ar.edu.utn.frsf.caperucita.models.CaperucitaEnvironment;
import ar.edu.utn.frsf.caperucita.scenary.Scenary;
import ar.edu.utn.frsf.caperucita.scenary.Scenary2;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class WebController {

    @PostMapping("/run")
    public ResponseEntity<ModelRun> run()
    {
        ModelRun modelRun = new ModelRun();

        Scenary scenary = new Scenary2();

        modelRun.setScenary(scenary);

        Caperucita agent = new Caperucita(scenary);
        CaperucitaEnvironment environment = new CaperucitaEnvironment(scenary);

        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulatorEx(environment, agent, modelRun);

        simulator.start();

        return ResponseEntity.ok(modelRun);
    }
}

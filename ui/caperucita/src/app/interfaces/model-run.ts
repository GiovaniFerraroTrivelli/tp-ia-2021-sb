import { CaperucitaIteration } from "./caperucita-iteration";
import { Scenary } from "./scenary";

export interface ModelRun {
	scenary: Scenary | null;
	caperucitaIterations: CaperucitaIteration[] | null;
}

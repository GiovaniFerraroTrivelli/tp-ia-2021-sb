import { CaperucitaIteration } from "./caperucita-iteration";
import { EnvironmentIteration } from "./environment-iteration";
import { Scenary } from "./scenary";

export interface ModelRun {
	scenary: Scenary | null;
	caperucitaIterations: CaperucitaIteration[] | null;
	environmentIterations: EnvironmentIteration[] | null;
	success: boolean;
}
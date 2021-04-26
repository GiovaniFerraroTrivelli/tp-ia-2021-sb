import { AgentAction } from "./agent-action";
import { CaperucitaState } from "./caperucita-state";

export interface CaperucitaIteration {
	caperucitaState: CaperucitaState;
	action: AgentAction;
}

import { Point } from "./point";

export interface Scenary {
	cakesTotal: number;
	caperucitaPosition: Point;
	flowersPosition: Point[];
	wolfPosition: Point;
	wolfSpawnPoints: Point[];
	forest: number[][];
	inicialForest: number[][];
}

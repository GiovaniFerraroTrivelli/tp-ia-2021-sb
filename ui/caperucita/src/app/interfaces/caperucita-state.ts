import { Point } from "./point";

export interface CaperucitaState {
	column: number[];
	flowersPosition: Point[];
	inicialKnownScenary: number[][];
	knownScenary: number[][];
	posicionActual: Point;
	posicionInicial: Point;
	row: number[];
	tortas: number;
	vidas: number;
}

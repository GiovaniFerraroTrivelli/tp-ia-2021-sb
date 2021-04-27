import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModelRun } from 'src/app/interfaces/model-run';
import { ApiService } from 'src/app/services/api.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
	success: boolean;
	modelRun: ModelRun;
	elapsedTime: number;
	loading: boolean;
	showScenary: boolean;
	iteration: number;
	startRun: FormGroup;
	caperucitaView: boolean;

	constructor(
		private apiService: ApiService
	) {
		this.success = false;
		this.modelRun = { scenary: null, caperucitaIterations: [], environmentIterations: [] };
		this.elapsedTime = 0;
		this.loading = false;
		this.showScenary = false;
		this.iteration = 0;
		this.startRun = new FormGroup({
			strategy: new FormControl(null, Validators.required),
		});
		this.caperucitaView = false;
	}

	ngOnInit(): void {
	}

	onSubmit() {
		let startTime = new Date().getTime();
		this.success = false;
		this.loading = true;
		this.showScenary = false;
		this.iteration = 0;

		this.elapsedTime = 0;
		this.apiService.post('run', this.modelRun, this.startRun.value).subscribe(data => {
			console.log(data);
			this.modelRun = data;
			this.success = true;
			this.loading = false;
			this.showScenary = true;
			this.iteration = 0;
			this.elapsedTime = new Date().getTime() - startTime;
			this.caperucitaView = true;
		});
	}

	setIteration(i: number) {
		this.iteration = i;
	}

	getCaperucitaIterationData() {
		return this.modelRun?.caperucitaIterations![this.iteration];
	}

	getEnvironmentIterationData() {
		return this.modelRun?.environmentIterations![this.iteration];
	}

	getIterationKnownScenary() {
		return this.getCaperucitaIterationData()?.caperucitaState.knownScenary;
	}

	getIterationScenary() {
		return this.getEnvironmentIterationData().currentForest;
	}

	isCaperucitaPosition(x: number, y: number) {
		return this.getCaperucitaIterationData()?.caperucitaState?.posicionActual?.x == x
			&& this.getCaperucitaIterationData()?.caperucitaState?.posicionActual?.y == y;
	}
}

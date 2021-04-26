import { Component, OnInit } from '@angular/core';
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

	constructor(
		private apiService: ApiService
	) {
		this.success = false;
		this.modelRun = { scenary: null, caperucitaIterations: [] };
		this.elapsedTime = 0;
		this.loading = false;
		this.showScenary = false;
		this.iteration = 0;
	}

	ngOnInit(): void {
	}

	start() {
		let startTime = new Date().getTime();
		this.success = false;
		this.loading = true;
		this.showScenary = false;
		this.iteration = 0;

		this.elapsedTime = 0;
		this.apiService.post('run', this.modelRun, []).subscribe(data => {
			console.log(data);
			this.modelRun = data;
			this.success = true;
			this.loading = false;
			this.showScenary = true;
			this.iteration = 0;
			this.elapsedTime = new Date().getTime() - startTime;

		});
	}

	setIteration(i: number) {
		this.iteration = i;
	}

	getIterationData() {
		return this.modelRun?.caperucitaIterations![this.iteration];
	}

	getIterationKnownScenary() {
		return this.getIterationData()?.caperucitaState.knownScenary;
	}

	isCaperucitaPosition(x: number, y: number) {
		return this.getIterationData()?.caperucitaState?.posicionActual?.x == x 
			&& this.getIterationData()?.caperucitaState?.posicionActual?.y == y;
	}
}

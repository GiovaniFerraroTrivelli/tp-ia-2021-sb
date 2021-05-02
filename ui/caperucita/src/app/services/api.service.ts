import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class ApiService {

	HOST: string = 'http://localhost';

	constructor(
		private http: HttpClient
	) { }

	get(endpoint: string, val: any) {
		return this.http.get<typeof val>(`${this.HOST}:8080/api/v1/${endpoint}`);
	}

	post(endpoint: string, val: any, data: any) {
		return this.http.post<typeof val>(`${this.HOST}:8080/api/v1/${endpoint}`, data);
	}
}

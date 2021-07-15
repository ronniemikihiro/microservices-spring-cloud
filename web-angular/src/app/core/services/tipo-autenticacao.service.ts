import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class TipoAutenticacaoService {

    url: string = environment.apiURLBase + '/api-user-data/tipoAutenticacao';

    constructor(protected http: HttpClient) {}

    listarTodos(): Observable<any> {
        return this.http.get<any>(this.url);
    }

}
import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Permissao } from '../entities/permissao';
import { GenericoService } from './generico.service';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class PermissaoService extends GenericoService<Permissao> {

    url: string = environment.apiURLBase + '/api-user-data/permissao';

    constructor(protected http: HttpClient) {
        super(http);
    }

    protected getUrl(): string {
        return this.url;
    }

    obterPorPerfil(idPerfil: number): Observable<Permissao[]> {
        return this.http.get<Permissao[]>(`${this.getUrl()}/listarPorPerfil/${idPerfil}`);
    } 

}

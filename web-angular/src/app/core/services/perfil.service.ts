import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GenericoService } from './generico.service';
import { Perfil } from '../entities/perfil';

@Injectable({
    providedIn: 'root'
})
export class PerfilService extends GenericoService<Perfil> {

    url: string = environment.apiURLBase + '/api-user-data/perfil';

    constructor(protected http: HttpClient) {
        super(http);
    }

    protected getUrl(): string {
        return this.url;
    }

}

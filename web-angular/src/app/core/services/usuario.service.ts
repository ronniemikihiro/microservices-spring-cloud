import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Usuario } from '../entities/usuario';
import { GenericoService } from './generico.service';

@Injectable({
    providedIn: 'root'
})
export class UsuarioService extends GenericoService<Usuario> {

    url: string = environment.apiURLBase + '/api-user/usuario';

    constructor(protected http: HttpClient) {
        super(http);
    }

    protected getUrl(): string {
        return this.url;
    }

}
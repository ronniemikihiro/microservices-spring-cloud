import { Empresa } from '../entities/empresa';
import { environment } from '../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GenericoService } from './generico.service';

@Injectable({
    providedIn: 'root'
})
export class EmpresaService extends GenericoService<Empresa> {

    url: string = environment.apiURLBase + '/api-user-data/empresa';

    constructor(protected http: HttpClient) {
        super(http);
    }

    protected getUrl(): string {
        return this.url;
    }

}

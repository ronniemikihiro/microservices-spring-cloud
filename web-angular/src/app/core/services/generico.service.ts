import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface IGenericoService<T> {
    listarTodos(): Observable<T[]>;
    obterPorId(id: number): Observable<T>;
    criar(entity: T): Observable<T>;
    alterar(entity: T): Observable<T>;
    deletar(id: number): Observable<any>;
}

export abstract class GenericoService<T> implements IGenericoService<T> {
    constructor(protected http: HttpClient) {}

    protected abstract getUrl(): string;

    listarTodos(): Observable<T[]> {
        return this.http.get<T[]>(this.getUrl());
    }

    obterPorId(id: number): Observable<T> {
        return this.http.get<T>(`${this.getUrl()}/${id}`);
    }
      
    criar(entity: T): Observable<T> {
        return this.http.post<T>(this.getUrl(), entity);
    }

    alterar(entity: T): Observable<T> {
        return this.http.put<T>(this.getUrl(), entity);
    }
    
    deletar(id: number): Observable<any> {
        return this.http.delete<any>(`${this.getUrl()}/${id}`);
    }
}
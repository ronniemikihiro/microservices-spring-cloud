import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { environment } from './../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly ACCESS_TOKEN = 'ACCESS_TOKEN';
  private readonly REFRESH_TOKEN = 'REFRESH_TOKEN';
  private readonly APY_SYSTEM_USER_NAME = 'APY_SYSTEM_USER_NAME';

  jwtPayload: any;
  tokenURL: string = environment.apiURLBase + environment.tokenUrl
  refreshTokenUrl: string = environment.apiURLBase + environment.refreshTokenUrl
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient,
              private router: Router) { }

  private getJwtPayload() {
    if(!this.jwtPayload) {
      const token = this.getToken();
      if(token) {
        this.jwtPayload = this.jwtHelper.decodeToken(token);
      }
    }
    return this.jwtPayload;
  }

  getToken() {
    return localStorage.getItem(this.ACCESS_TOKEN);
  }

  getRefreshToken() {
    return localStorage.getItem(this.REFRESH_TOKEN);
  }

  salvarToken(response: any) {
    this.deletarToken();
    this.jwtPayload = this.jwtHelper.decodeToken(response.accessToken);
    localStorage.setItem(this.ACCESS_TOKEN, response.accessToken);
    localStorage.setItem(this.REFRESH_TOKEN, response.refreshToken);
  }

  deletarToken() {
    localStorage.removeItem(this.ACCESS_TOKEN);
    localStorage.removeItem(this.REFRESH_TOKEN);
    this.jwtPayload = null;
  }

  getUsername() {
    return localStorage.getItem(this.APY_SYSTEM_USER_NAME);
  }

  salvarUsername(username : string) {
    this.deletarUsername();
    localStorage.setItem(this.APY_SYSTEM_USER_NAME, username);
  }

  deletarUsername() {
    localStorage.removeItem(this.APY_SYSTEM_USER_NAME);
  }

  isTokenExpirado(): boolean {
    const accessToken = this.getToken();
    return !accessToken || this.jwtHelper.isTokenExpired(accessToken);
  }

  isRefreshTokenExpirado(): boolean {
    const refreshToken = this.getRefreshToken();
    return !refreshToken || this.jwtHelper.isTokenExpired(refreshToken);
  }

  isAuthenticated() : boolean {
    return !this.isTokenExpirado();
  }

  getUsuarioAutenticado() {
    const accessToken = this.getToken();
    return accessToken ? this.jwtHelper.decodeToken(accessToken).username : null;
  }

  temRole(role: string) {
    return this.getJwtPayload() && this.getJwtPayload().authorities.includes(role);
  }

  temQualquerRole(roles: Array<string>) {
    return roles.find(role => this.temRole(role)) !== undefined ? true : false;
  }

  login(username: string, senha: string): Observable<any> {
    const body = `username=${username}&password=${senha}`;
    const headers = { 'Content-Type': 'application/x-www-form-urlencoded' }
    
    return this.http.post(this.tokenURL, body, { headers });
  }

  refreshToken(): Observable<any> {
    const body = `refreshToken=${this.getRefreshToken()}`;
    const headers = { 'Content-Type': 'application/x-www-form-urlencoded' }

    return this.http.post(this.refreshTokenUrl, body, { headers });
  }

  deslogar() {
    this.deletarToken();
    this.router.navigate(['/auth/login']);
  }
}

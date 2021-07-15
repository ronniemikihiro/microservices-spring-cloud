import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivateChild } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(
    private authService: AuthService,
    private router: Router
  ){}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    if(this.authService.isTokenExpirado()) {
      if(!this.authService.getRefreshToken() || this.authService.isRefreshTokenExpirado()) {
        console.log('Auth Guard ==> Usuário não autenticado!');
        this.router.navigate(['/auth/login']);
        return false;
      }

      console.log('Auth Guard ==> Token expirado! Gerando novo token...');

      this.authService.refreshToken().subscribe(response => {
        this.authService.salvarToken(response);
        console.log('Auth Guard ==> Novo token gerado com sucesso...');
        return true;
      }, errorResponse => {
        console.error('Auth Guard ==> Usuário não autenticado!');
        this.router.navigate(['/auth/login']);
        return false;
      });
          
    } else if (next.data.roles && !this.authService.temQualquerRole(next.data.roles)) {
      this.router.navigate(['/auth/nao-autorizado']);
      return false; 
    }
    
    return true;
  }

  canActivateChild(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (next.data.roles && !this.authService.temQualquerRole(next.data.roles)) {
      this.router.navigate(['/auth/nao-autorizado']);
      return false; 
    }

    return true;
  }
}

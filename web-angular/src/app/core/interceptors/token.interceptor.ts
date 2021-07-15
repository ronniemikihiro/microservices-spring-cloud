import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { MensagemService } from '../services/mensagem.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
              private router: Router,
              private mensagemService: MensagemService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let accessToken = this.authService.getToken();
    
    if(accessToken && !request.url.endsWith('/auth/token')) {
      request = this.addToken(request, accessToken);
    }
    
    return next.handle(request).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 401) {
        console.log('Token Interceptor ==> Token expirado! Gerando novo token...');

        return this.chamarRefreshToken(request, next).pipe(catchError(error => {
          if(this.authService.isTokenExpirado()) {
            console.error('Token Interceptor ==> Não foi possível gerar um novo token... :(');
            this.authService.deslogar();
          }
          throw error;
        }));
      } else  {
        if (error.error.error === 'invalid_grant') {
          this.mensagemService.alerta('Usuário ou senha inválido!');
        } else if (error.error.status === 500) {
          error.error.error = 'Erro interno do servidor';
          this.mensagemService.erro(error);
          this.router.navigate(['/home']);
        } else if (error.error.status === 503) {
          error.error.error = 'Serviço: ' + error.url + ' indisponível...';
          this.mensagemService.erro(error);
        } else if (error.error.status === 504) {
          error.error.error = 'Tempo esgotado! Servidor não respondeu...';
          this.mensagemService.erro(error);
        }
        throw error;
      }
    }));
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        'Authorization': `Bearer ${token}`
      }
    });
  }

  private chamarRefreshToken(request: HttpRequest<any>, next: HttpHandler) {
    return this.authService.refreshToken().pipe(switchMap((response) => {
      this.authService.salvarToken(response);
      const accessToken = this.authService.getToken();
      console.log('Token Interceptor ==> Novo token gerado com sucesso...');
      return next.handle(this.addToken(request, accessToken));
    }));
  }

}

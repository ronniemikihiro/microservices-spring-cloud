import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  usuarioLogado: any;

  constructor(private authService: AuthService,
              private titleService: Title) {
  }

  ngOnInit() {
    this.usuarioLogado = this.authService.getUsuarioAutenticado();
    this.titleService.setTitle('Home Page');
  }
}

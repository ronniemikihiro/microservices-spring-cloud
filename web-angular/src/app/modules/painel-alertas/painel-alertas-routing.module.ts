import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';
import { PainelAlertasComponent } from './painel-alertas.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard], 
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PainelAlertasComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelAlertasRoutingModule { }

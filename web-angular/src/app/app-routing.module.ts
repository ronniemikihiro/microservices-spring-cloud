import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';

const appRoutes: Routes = [
    {
        path: 'auth',
        loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)
        //loadChildren: './modules/auth/auth.module#AuthModule'
    },
    {
        path: 'home',
        loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule),
        //loadChildren: './modules/home/home.module#HomeModule',
        canActivate: [AuthGuard]
    },
    /* CADASTROS */
    {
        path: 'empresas',
        loadChildren: () => import('./modules/empresas/empresas.module').then(m => m.EmpresasModule),
        //loadChildren: './modules/empresas/empresas.module#EmpresasModule',
        canActivate: [AuthGuard]
    },
    /* PAINÉIS */
    {
        path: 'painel-alertas',
        loadChildren: () => import('./modules/painel-alertas/painel-alertas.module').then(m => m.PainelAlertasModule),
        canActivate: [AuthGuard]
    },
    /* SEGURANÇA */
    {
        path: 'usuarios',
        loadChildren: () => import('./modules/usuarios/usuarios.module').then(m => m.UsuariosModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'perfis',
        loadChildren: () => import('./modules/perfis/perfis.module').then(m => m.PerfisModule),
        canActivate: [AuthGuard]
    },
    {
        path: 'permissoes',
        loadChildren: () => import('./modules/permissoes/permissoes.module').then(m => m.PermissoesModule),
        canActivate: [AuthGuard]
    },
    {
        path: '**',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes, { relativeLinkResolution: 'legacy' })
    ],
    exports: [RouterModule],
    providers: []
})
export class AppRoutingModule { }

import { Component, OnInit, ChangeDetectorRef, OnDestroy, AfterViewInit, ViewEncapsulation } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { BehaviorSubject } from 'rxjs';

import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';
import { LoadingService } from 'src/app/core/services/loading.service';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class LayoutComponent implements OnInit, OnDestroy, AfterViewInit {

    private _mobileQueryListener: () => void;
    mobileQuery: MediaQueryList;
    showSpinner: boolean;
    userName: string;
    isAdmin: boolean;

    //private autoLogoutSubscription: Subscription;

    constructor(private changeDetectorRef: ChangeDetectorRef,
                private media: MediaMatcher,
                public loadingService: LoadingService,
                private authService: AuthService
                /*private authGuard: AuthGuard*/) {

        this.mobileQuery = this.media.matchMedia('(max-width: 600px)');
        this._mobileQueryListener = () => changeDetectorRef.detectChanges();
        // tslint:disable-next-line: deprecation
        this.mobileQuery.addListener(this._mobileQueryListener);
    }

    ngOnInit(): void {
        const user = this.authService.getUsuarioAutenticado();

        this.isAdmin = true;
        this.userName = user;
    }

    ngOnDestroy(): void {
        // tslint:disable-next-line: deprecation
        this.mobileQuery.removeListener(this._mobileQueryListener);
        //this.autoLogoutSubscription.unsubscribe();
    }

    ngAfterViewInit(): void {
        this.changeDetectorRef.detectChanges();
    }

    deslogar() {
        this.authService.deslogar();
    }

    view() : BehaviorSubject<boolean> {
        return this.loadingService.visibility;
    }
}

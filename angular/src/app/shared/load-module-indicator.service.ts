import { Injectable } from '@angular/core';
import { NavigationEnd, RouteConfigLoadStart, Router, RouterEvent } from '@angular/router';
import { SnackbarService } from './snakebar.service';

@Injectable()
export class LoadModuleIndicatorService {

  constructor(private router: Router, public snackBar: SnackbarService) {
    this.onInit();
  }

  private onInit() {
    this.router.events.subscribe((event: RouterEvent): void => {
      if (event instanceof RouteConfigLoadStart) {
        this.snackBar.openMySnackBar(`loading module ${event.route.path} ...`);
      } else if (event instanceof NavigationEnd) {
        this.snackBar.dismiss();
      }
    });
  }
}

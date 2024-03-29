import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { LoaderService } from './loader.service';
import { Router } from '@angular/router';
import { SnackBarService } from './snack-bar.service';
import { SessionService } from '../shared';
import { DialogMessageService } from './dialog-message.component';
import { environment } from 'src/environments/environment';
import { delay, tap } from 'rxjs/operators';
// import { SessionService } from '../shared';

@Injectable({
  providedIn: 'root'
})
export class LoaderInterceptor implements HttpInterceptor {
  private requests: HttpRequest<any>[] = [];
  // public p: Observable<any>;
  // i = 0;
  cachedRequests: Array<HttpRequest<any>> = [];

  private cache: Map</*HttpRequest<any>*/ string, HttpResponse<any>> = new Map();

  max = 0;
  percentage = 0;

  constructor(private loaderService: LoaderService, public router: Router
    , public snackBar: SnackBarService, private session: SessionService
    , public dialog: DialogMessageService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.cache = this.getCache();
    const isBackOffice = !this.router.url.includes('shop');

    // if (isBackOffice || (req.method !== 'GET')) {
    //   return this.handleRequest(req, next);
    // }
    const key = btoa(JSON.stringify(req));

    if (req.headers.get('reset')) {
      this.cache.delete(key);
      this.setCache(this.cache);
    }

    const cachedResponse = new HttpResponse<any>(this.cache.get(key));

    if (cachedResponse) {
      return of(cachedResponse.clone())
    } else {
      return this.handleRequest(req, next).pipe(tap(stateEvent => {
        if (stateEvent instanceof HttpResponse) {
          this.cache.set(key, stateEvent.clone());
          this.setCache(this.cache);
        }
      }));
    }

  }

  setCache(map: Map<string, HttpResponse<any>>) {
    localStorage.setItem('cache', JSON.stringify([...map]));
  }

  getCache() {
    if (environment.production) {
      return this.cache;
    }
    try {
      const r = localStorage.getItem('cache');
      return r ? new Map<string, HttpResponse<any>>(JSON.parse(r)) : this.cache;
    } catch (error) {
      return this.cache;
    }
  }

  handleRequest(req: HttpRequest<any>, next: HttpHandler) {
    this.requests.push(req);
    this.calculPercentage(this.requests.length);
    //

    //
    const o = new Observable(observer => {
      const reqAddedToken = req.clone({
        setHeaders: {
          // 'Content-Type': 'application/json',
          Authorization: `Bearer ${this.session.token}`,
          // Authorization: `${this.session.token}`,
        }
        // this.headers = new HttpHeaders({
        //   'Content-Type': 'application/json',
        //   'Authorization': 'Bearer ' + this.accessToken
        // });
      });

      const s = next.handle(reqAddedToken).subscribe(
        event => {
          if (event instanceof HttpResponse) {
            this.removeRequest(req);
            observer.next(event);
            const code = event.status === 200 && event.url.includes('post') ? 201 : event.status;
            this.snackBar.manageStatusCode(code);
          }
        },
        err => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401 || err.status === 403) {
              // this.toast.toastError(err.status); // , err.statusText);
              // this.snackBar.notifyAlert(`${err.status}: ${err.statusText}`);
              console.log(err.status, err.statusText);
              // this.session.doSignOut();
              this.router.navigate(['/auth']);
              this.snackBar.manageStatusCode(err.status);
            } else {
              // console.log(err);
              // this.toast.toastError(err.error);
              const er = err.error ? `${err.status}: ${err.error.Description}` : `${err.status}`
              this.snackBar.manageStatusCode(err.status);
              this.dialog.openDialog(err);
              // this.snackBar.notifyAlert(er);
              // this.snackBar.openSnackBar(`${err.status} : ${err.error.Description}`);
            }
          }
          this.removeRequest(req);
          observer.error(err);
        },
        () => {
          this.removeRequest(req);
          observer.complete();
        }
      );
      // teardown logic in case of cancelled requests
      return () => {
        this.removeRequest(req);
        s.unsubscribe();
      };
    });
    //
    return o as Observable<HttpEvent<any>>;
  }

  //


  removeRequest(req: HttpRequest<any>) {
    const i = this.requests.indexOf(req);
    if (i >= 0) {
      this.requests.splice(i, 1);
      this.calculPercentage(this.requests.length);
    }
  }

  public collectFailedRequest(request): void {
    this.cachedRequests.push(request);
  }
  public retryFailedRequests(): void {
    // retry the requests. this method can
    // be called after the token is refreshed
  }

  //


  calculPercentage(length: number) {
    if (length === 0) {
      this.percentage = 100;
      this.max = 0;
      this.loaderService.isLoading.next({ isBegin: this.requests.length > 0, count: +this.percentage.toFixed(0) });
    } else {
      this.max = this.max > length ? this.max : length;
      this.percentage = 100 - ((length * 100) / this.max);
      this.loaderService.isLoading.next({ isBegin: true, count: +this.percentage.toFixed(0) });
    }

    // console.log(length, this.percentage)
  }
}

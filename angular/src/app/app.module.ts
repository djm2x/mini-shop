import { LoaderModule } from './loader/loader.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Injector } from '@angular/core';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InjectService } from './inject.service';
import { AppRoutingModule } from './app-routing.module';
import { MessageComponent } from './shared/snakebar.service';
import { LoaderInterceptor } from './loader/loader-interceptor';
import { DeleteComponent } from './components/delete/delete.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { LoadModuleIndicatorService } from './shared/load-module-indicator.service';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import localeFr from '@angular/common/locales/fr';
import { registerLocaleData } from '@angular/common';
registerLocaleData(localeFr);
@NgModule({
  declarations: [
    AppComponent,
    MessageComponent,
    DeleteComponent,
  ],
  imports: [
    // ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    // MatModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    //
    // FormsModule,
    BrowserAnimationsModule,
    LoaderModule,
    // MatSnackBarModule,
    // MatProgressSpinnerModule,
  ],
  providers: [
    LoadModuleIndicatorService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptor,
      multi: true
    },
    {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private injector: Injector) {    // Create global Service Injector.
    InjectService.injector = this.injector;
  }
}

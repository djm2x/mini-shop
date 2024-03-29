import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoaderComponent } from './loader.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { DialogMessageComponent, DialogMessageService } from './dialog-message.component';

@NgModule({
  declarations: [
    LoaderComponent,
    DialogMessageComponent,
  ],
  imports: [
    CommonModule,
    MatProgressBarModule,
    MatProgressSpinnerModule
  ],
  exports: [
    LoaderComponent,
  ],
  providers: [
    DialogMessageService,
  ]
})
export class LoaderModule { }

import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Inject, EventEmitter, Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


/**
 *
 */
@Injectable()
export class DialogMessageService {

  constructor(public dialog: MatDialog) {}

  openDialog(model: HttpErrorResponse) {
    const dialogRef = this.dialog.open(DialogMessageComponent, {
      width: '700px',
      disableClose: false,
      data: { model }
    });

    return dialogRef.afterClosed();
  }
 }

/**
 *
 */
@Component({
  selector: 'app-dialog-message',
  template: `
    <div class="dialog p-2">
        <div mat-dialog-content>
          <p style="font-weight: bold; line-height: 2.5;">
            {{model.error?.exception}} => {{model.error?.message}}
            {{model.error?.error?.message}}

            {{model.message}}
          </p>
        </div>
    </div>
  `,
  styles: [``]
})
export class DialogMessageComponent implements OnInit {

  model: HttpErrorResponse = null;
  constructor(public dialogRef: MatDialogRef<any>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.model = this.data.model;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onOkClick(): void {
    this.dialogRef.close('ok');
  }

}


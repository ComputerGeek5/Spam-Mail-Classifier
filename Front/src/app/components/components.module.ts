import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ComposeComponent } from './compose/compose.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MDBBootstrapModule} from 'angular-bootstrap-md';

@NgModule({
    imports: [
      CommonModule,
      RouterModule,
      NgbModule,
      ReactiveFormsModule,
      MDBBootstrapModule.forRoot()
    ],
    declarations: [
      FooterComponent,
      NavbarComponent,
      SidebarComponent,
      ComposeComponent
    ],
    exports: [
      FooterComponent,
      NavbarComponent,
      SidebarComponent,
      ComposeComponent
    ]
})
export class ComponentsModule {}

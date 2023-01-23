import { Routes } from '@angular/router';

import { InboxComponent } from '../../pages/inbox/inbox.component';
import { SentComponent } from '../../pages/sent/sent.component';
import {AuthGuard} from '../../security/guard/auth.guard';
import {Role} from '../../model/Role';
import {MailComponent} from '../../pages/mail/mail.component';
import {JunkComponent} from '../../pages/junk/junk.component';
import {ReadComponent} from '../../pages/read/read.component';

export const AdminLayoutRoutes: Routes = [
  {
    path: 'inbox',
    component: InboxComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] },
  },
  {
    path: 'message',
    component: MailComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] },
  },
  {
    path: 'junk',
    component: JunkComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] },
  },
  {
    path: 'read',
    component: ReadComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] },
  },
  {
    path: 'sent',
    component: SentComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.USER] }
  },
];

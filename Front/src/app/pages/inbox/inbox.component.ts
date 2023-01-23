import { Component, OnInit } from "@angular/core";
import {Router} from '@angular/router';
import {Mail} from '../../model/Mail';
import {MailService} from '../../service/mail/mail.service';
import {AuthService} from '../../security/auth/auth.service';
import {Permission} from '../../model/Permission';

@Component({
  selector: "app-inbox",
  templateUrl: "inbox.component.html",
  styleUrls: ['./inbox.component.scss']
})
export class InboxComponent implements OnInit {
  success = "";
  error = "";

  inbox: Mail[] = [];

  permission = Permission;

  constructor(private router: Router, private mailService: MailService, private authService: AuthService) {
    let navigation = this.router.getCurrentNavigation();

    if (navigation.extras !== null && navigation.extras.state !== undefined) {
      let state = navigation.extras.state;

      if (state.success != undefined) {
        this.success = state.success;
      }

      if (state.error != undefined) {
        this.error = state.error;
      }
    }
  }

  ngOnInit() {
    let userId: number = this.authService.getSessionUser().id;

    this.mailService.getInbox(userId).subscribe(
      (result) => {
        this.inbox = result;
      }
    );
  }

  handleSuccessMessage(value) {
    this.success = value;
  }

  handleErrorMessage(value) {
    this.error = value;
  }
}

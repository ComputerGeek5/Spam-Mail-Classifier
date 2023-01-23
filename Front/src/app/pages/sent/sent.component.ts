import { Component, OnInit } from "@angular/core";
import {Permission} from '../../model/Permission';
import {Mail} from '../../model/Mail';
import {AuthService} from '../../security/auth/auth.service';
import {MailService} from '../../service/mail/mail.service';

@Component({
  selector: "app-sent",
  templateUrl: "sent.component.html",
  styleUrls: ['./sent.component.scss']
})
export class SentComponent implements OnInit {
  sent: Mail[] = [];

  permission = Permission;

  constructor(private authService: AuthService, private mailService: MailService) {}

  ngOnInit() {
    let userId: number = this.authService.getSessionUser().id;

    this.mailService.getSent(userId).subscribe(
      (result) => {
        this.sent = result;
      }
    );
  }
}

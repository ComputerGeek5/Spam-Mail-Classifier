import { Component, OnInit } from '@angular/core';
import {Mail} from '../../model/Mail';
import {Permission} from '../../model/Permission';
import {Router} from '@angular/router';
import {MailService} from '../../service/mail/mail.service';
import {AuthService} from '../../security/auth/auth.service';

@Component({
  selector: 'app-junk',
  templateUrl: 'junk.component.html',
  styleUrls: ['./junk.component.scss']
})
export class JunkComponent implements OnInit {
  success = "";
  error = "";

  junk: Mail[] = [];

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

    this.mailService.getJunk(userId).subscribe(
      (result) => {
        this.junk = result;
      }
    );
  }
}

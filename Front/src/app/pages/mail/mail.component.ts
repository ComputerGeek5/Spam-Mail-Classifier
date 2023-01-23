import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationExtras, Router} from '@angular/router';
import {Mail} from '../../model/Mail';
import {MailService} from '../../service/mail/mail.service';
import {SpamDetectorService} from '../../service/ml/spam-detector.service';

@Component({
  selector: 'app-mail',
  templateUrl: './mail.component.html',
  styleUrls: ['./mail.component.scss']
})
export class MailComponent implements OnInit {
  mail: Mail;
  permissions: string[];

  constructor(private mailService: MailService, private spamDetectorService: SpamDetectorService, private route: ActivatedRoute, private router: Router) {
    let state = this.router.getCurrentNavigation().extras.state;
    this.mail = state.mail;

    let permissions = state.permissions;

    this.permissions = permissions;
  }

  ngOnInit(): void {
    if (this.mail.spam === null) {
      let isSpam: boolean;
      try {
        this.spamDetectorService.classify(this.mail.message)
          .subscribe(
            (result) => {
              isSpam = result;
              if (isSpam) {
                if (window.confirm("This mail has been classified as spam.\nMark as spam and move to Junk ?")) {
                  this.markAsSpam();
                  console.log("Marked as spam");
                } else {

                }
              }
            }
          );
      } catch (error) {
        isSpam = false;
      }
    }
  }

  delete() {
    try {
      this.mailService.delete(this.mail.id).subscribe(
        (response) => {
          const navigationExtras: NavigationExtras = {state: {success: response.message}};
          this.router.navigate(['inbox'], navigationExtras);
        }
      )
    } catch (error) {
      const navigationExtras: NavigationExtras = {state: {error: error.message}};
      this.router.navigate(['inbox'], navigationExtras);
    }
  }

  markAsSpam() {
    let id = this.mail.id;
    let spam = true;
    let redirectUrl = "junk";
    let message = "Mail marked as spam";

    this.mailService.markAndRedirectWithMessage(id, spam, redirectUrl, message);
  }

  unMarkAsSpam() {
    let id = this.mail.id;
    let spam = null;
    let redirectUrl = "inbox";
    let message = "Mail removed from junk";

    this.mailService.markAndRedirectWithMessage(id, spam, redirectUrl, message);
  }


  markAsRead() {
    let id = this.mail.id;
    let spam = false;
    let redirectUrl = "read";
    let message = "Mail marked as read";

    this.mailService.markAndRedirectWithMessage(id, spam, redirectUrl, message);
  }

  unMarkAsRead() {
    let id = this.mail.id;
    let spam = null;
    let redirectUrl = "inbox";
    let message = "Mail unmarked successfully";

    this.mailService.markAndRedirectWithMessage(id, spam, redirectUrl, message);
  }
}

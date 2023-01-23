import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, NavigationExtras, Router} from '@angular/router';
import {AuthService} from '../../security/auth/auth.service';
import {LogInRequest} from '../../model/request/LogInRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class LoginComponent implements OnInit {
  error = '';
  success = '';

  private isLoged = false;
  form = new FormGroup({
    username: new FormControl('', [
      Validators.required,
    ]),
    password: new FormControl('', [
      Validators.required,
    ]),
  });

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) {
    let navigation = this.router.getCurrentNavigation();

    if (navigation.extras !== null && navigation.extras.state !== undefined) {
      let state = navigation.extras.state as {data: string};
      this.success = state.data;
    }
  }

  ngOnInit() {
    this.isLoged = this.authService.isLogedIn();

    if(this.isLoged) {
      this.router.navigate(['inbox']);
    }
  }

  logIn() {
    const username = this.form.value.username;
    const password = this.form.value.password;

    const request: LogInRequest = new LogInRequest();
    request.username = username;
    request.password = password;

    this.authService.logIn(request).subscribe(
      (response) => {
        console.log(response);

        if (response.status != 200) {
          this.error = response.message;
        } else {
          const navigationExtras: NavigationExtras = {state: {success: response.message}};
          this.router.navigate(['inbox'], navigationExtras);
        }
      },
      (error) => {
        console.log(error);
        this.error = error;
      }
    );
  }
}

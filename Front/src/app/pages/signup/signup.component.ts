import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NavigationExtras, Router} from '@angular/router';
import {AuthService} from '../../security/auth/auth.service';
import {SignUpRequest} from '../../model/request/SignUpRequest';
import {MatDatepickerModule} from '@angular/material/datepicker';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  private dateToString = (date) => `${date.year}-${date.month}-${date.day}`;

  error = '';

  private isLoged = false;
  form = new FormGroup({
    username: new FormControl('', [
      Validators.required,
    ]),
    password: new FormControl('', [
      Validators.required,
    ]),
    firstName: new FormControl('', [
      Validators.required,
    ]),
    lastName: new FormControl('', [
      Validators.required,
    ]),
    gender: new FormControl('', [
      Validators.required,
    ]),
    birthday: new FormControl('', [
      Validators.required,
    ]),
    occupation: new FormControl('', [

    ]),
    location: new FormControl('', [

    ])
  });

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.isLoged = this.authService.isLogedIn();

    if(this.isLoged) {
      this.router.navigate(['inbox']);
    }
  }

  signUp() {
    const username = this.form.value.username;
    const password = this.form.value.password;
    const firstName = this.form.value.firstName;
    const lastName = this.form.value.lastName;
    const gender = this.form.value.gender;
    const birthday = this.dateToString(this.form.value.birthday);
    const occupation = this.form.value.occupation;
    const location = this.form.value.location;

    const signUpRequest: SignUpRequest = new SignUpRequest();
    signUpRequest.username = username;
    signUpRequest.password = password;
    signUpRequest.firstName = firstName;
    signUpRequest.lastName = lastName;
    signUpRequest.gender = gender;
    signUpRequest.birthday = birthday;
    signUpRequest.occupation = occupation;
    signUpRequest.location = location;

    this.authService.signUp(signUpRequest)
      .subscribe(
        (response) => {
          console.log(response);

          if (response.status != 200) {
            this.error = response.message;
          } else {
            const navigationExtras: NavigationExtras = {state: {data: 'Sign Up Successful.'}};
            this.router.navigate(['login'], navigationExtras);
          }
        },
        (error) => {
          console.log(error);
          this.error = error.message;
        }
      );
  }
}

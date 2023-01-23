import {Role} from './Role';
import {Mail} from './Mail';
import {LogInResponse} from './response/LogInResponse';

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  gender: string;
  birthday: string;
  occupation: string;
  location: string;
  inbox: Mail[];
  sent: Mail[];
  role: Role;

  constructor() {
  }

  fromResponse(response: LogInResponse) {
    this.id = response.data.user.id;
    this.username = response.data.user.username;
    this.password = response.data.user.password;
    this.firstName = response.data.user.firstName;
    this.lastName = response.data.user.lastName;
    this.gender = response.data.user.gender;
    this.birthday = response.data.user.birthday;
    this.occupation = response.data.user.occupation;
    this.location = response.data.user.location;
    this.role = response.data.user.role;
  }
}

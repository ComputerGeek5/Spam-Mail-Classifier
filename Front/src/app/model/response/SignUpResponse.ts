import {Mail} from '../Mail';
import {Role} from '../Role';

export class SignUpResponse {
  data: {
    id: number,
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
  };
  status: number;
  message: string;
}

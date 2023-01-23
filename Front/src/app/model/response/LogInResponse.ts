import {Mail} from '../Mail';
import {Role} from '../Role';

export class LogInResponse {
  data: {
    user: {
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

    token: string;
  };

  status: number;
  message: string;
}

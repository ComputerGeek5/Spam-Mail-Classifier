import {User} from '../User';

export class ComposeRequest {
  subject: string;
  message: string;
  receiver: User;
  sender: User;
}

import {Answer} from './answer.model';

export interface Test {
  id: string;
  question: string;
  branch: string;
  type: string;
  answers: Answer[];
}

import {Answer} from './answer.model';
import {Question} from "./question.model";

export interface Test {
  id: string;
  question: string;
  codeInQuestion: string;
  branch: string;
  type: string;
  matchQuestion: Question[];
  answers: Answer[];
}

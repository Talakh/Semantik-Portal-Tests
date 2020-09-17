import {Answer} from './answer.model';

export interface OneCorrectAnswerTest {
  id: string;
  question: string;
  branch: string;
  answers: Answer[];
}

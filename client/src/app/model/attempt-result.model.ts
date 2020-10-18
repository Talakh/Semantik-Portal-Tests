import {BranchChild} from "./branch-child.model";

export interface AttemptResult {
  questionsCount: number;
  correctAnswersCount: number;
  passed: boolean;
  percent: number;
  grade: string;
  branch: BranchChild;
  topicsToRepeat: TopicToRepeat[];
}

export interface TopicToRepeat {
  domainUrl: string;
  domainName: string;
}

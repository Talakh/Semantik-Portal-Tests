import {Test} from "./test.model";
import {BranchChild} from "./branch-child.model";

export interface Attempt {
  attemptId: string;
  branch: BranchChild;
  tests: Test[];
}

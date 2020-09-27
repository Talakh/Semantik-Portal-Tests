import {Component, OnInit} from '@angular/core';
import {TestsService} from "../service/tests.service";
import {finalize} from "rxjs/operators";
import {Test} from "../model/test.model";
import {Answer} from "../model/answer.model";

enum TestTypeEnum {
  ONE_CORRECT_ANSWER = 'ONE_CORRECT_ANSWER',
  SEVERAL_CORRECT_ANSWER = 'SEVERAL_CORRECT_ANSWER',
  MATCH = 'MATCH'
}

@Component({
  selector: 'app-tests',
  templateUrl: './tests.component.html',
  styleUrls: ['./tests.component.css']
})
export class TestsComponent implements OnInit {
  branch: string = "Select the subject";
  difficultLevel: string = "Select the difficult level";
  tests: Test[] = [];
  currentTestIndex: number;
  currentTest: Test;
  testTypes = TestTypeEnum;
  needCheck: boolean = true;
  answerId: string;
  answerIds: string[] = [];

  constructor(private testsService: TestsService) {
  }

  ngOnInit(): void {
  }

  createTests() {
    console.log("branch " + this.branch + " level " + this.difficultLevel);
    this.testsService.create(this.branch, this.difficultLevel)
      .pipe(finalize(() => {
        this.currentTestIndex = 0;
        this.currentTest = this.tests[this.currentTestIndex];
      }))
      .subscribe((res: Test[]) => {
        this.tests = res;
      }).add();
  }

  nextTest() {
    this.currentTest = this.tests[++this.currentTestIndex];
    this.needCheck = true;
  }

  checkOneAnswer(testId: string) {
    this.testsService.checkAnswer({"testId": testId, "answerId": this.answerId})
      .subscribe((res) => console.log(res));
    this.needCheck = false;
  }

  changeAnswer(answerId: string) {
    this.answerId = answerId;
  }

  changeBranch(branch: string) {
    this.branch = branch;
  }

  addOrRemoveAnswer(answerId: string) {
    if (this.answerIds.includes(answerId)){
      const index = this.answerIds.indexOf(answerId);
      if (index > -1) {
        this.answerIds.splice(index, 1);
      }
    } else {
      this.answerIds.push(answerId);
    }
  }

  checkSeveralAnswer(testId: string) {
    console.log("Test: " + testId + " answers: " + this.answerIds);
    this.testsService.checkAnswer({"testId": testId, "answerIds": this.answerIds})
      .subscribe((res) => console.log(res));
    this.needCheck = false;
  }
}

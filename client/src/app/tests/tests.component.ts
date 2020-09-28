import {Component, OnInit} from '@angular/core';
import {TestsService} from "../service/tests.service";
import {finalize} from "rxjs/operators";
import {Test} from "../model/test.model";
import {Answer} from "../model/answer.model";
import {Attempt} from "../model/attempt.model";
import {AttemptService} from "../service/attempt.service";

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
  attempt: Attempt;
  currentTestIndex: number;
  currentTest: Test;
  testTypes = TestTypeEnum;
  needCheck: boolean = true;
  answerId: string;
  answerIds: string[] = [];
  isCorrect: boolean;
  correctAnswerId: string;
  correctAnswerIds: string[];

  constructor(private testsService: TestsService,
              private attemptService: AttemptService) {
  }

  ngOnInit(): void {
  }

  createTests() {
    console.log("branch " + this.branch + " level " + this.difficultLevel);
    this.attemptService.create(this.branch, this.difficultLevel)
      .pipe(finalize(() => {
        this.currentTestIndex = 0;
        this.currentTest = this.attempt.tests[this.currentTestIndex];
      }))
      .subscribe((res: Attempt) => {
        this.attempt = res;
      }).add();
  }

  nextTest() {
    this.currentTest = this.attempt.tests[++this.currentTestIndex];
    this.needCheck = true;
  }

  checkOneAnswer(testId: string) {
    this.testsService.checkAnswer({"testId": testId, "answerId": this.answerId})
      .subscribe((res) => {
        console.log(res);
        this.isCorrect = res.isTrue;
        this.correctAnswerId = res.correctId;
      });
    this.needCheck = false;
  }

  changeAnswer(answerId: string) {
    this.answerId = answerId;
  }

  changeBranch(branch: string) {
    this.branch = branch;
  }

  addOrRemoveAnswer(answerId: string) {
    if (this.answerIds.includes(answerId)) {
      const index = this.answerIds.indexOf(answerId);
      if (index > -1) {
        this.answerIds.splice(index, 1);
      }
    } else {
      this.answerIds.push(answerId);
    }
  }

  checkSeveralAnswer(testId: string) {
    this.testsService.checkAnswer({"testId": testId, "answerIds": this.answerIds})
      .subscribe((res) => {
        console.log(res);
        this.correctAnswerIds = res.correctIds;
      });
    this.needCheck = false;
  }
}

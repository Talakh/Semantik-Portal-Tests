import {Component, OnInit} from '@angular/core';
import {TestsService} from "../service/tests.service";
import {finalize} from "rxjs/operators";
import {Test} from "../model/test.model";
import {Attempt} from "../model/attempt.model";
import {AttemptService} from "../service/attempt.service";
import {BranchChild} from "../model/branch-child.model";
import {AttemptResult} from "../model/attempt-result.model";

enum TestTypeEnum {
  ONE_CORRECT_ANSWER = 'ONE_CORRECT_ANSWER',
  SEVERAL_CORRECT_ANSWER = 'SEVERAL_CORRECT_ANSWER',
  MATCH = 'MATCH'
}

@Component({
  selector: 'app-tests',
  templateUrl: './tests.component.html',
  styleUrls: ['./tests.component.scss']
})
export class TestsComponent implements OnInit {
  branch: BranchChild;
  difficultLevel: string = "Select the difficult level";
  branches: BranchChild[];
  attempt: Attempt;
  currentTestIndex: number;
  currentTest: Test;
  testTypes = TestTypeEnum;
  needCheck: boolean = true;
  answerId: string;
  answerIds: string[] = [];
  answerMap = {};
  isCorrect: boolean;
  correctAnswerId: string;
  correctAnswerIds: string[];
  correctAnswerMap: {};

  attemptResult: AttemptResult;

  constructor(private testsService: TestsService,
              private attemptService: AttemptService) {
  }

  ngOnInit(): void {
    this.testsService.branches()
      .subscribe((res) => this.branches = res);
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
        console.log('this.attempt', this.attempt)
      }).add();
  }

  nextTest() {
    this.currentTest = this.attempt.tests[++this.currentTestIndex];
    this.needCheck = true;
    this.answerIds = [];
    this.answerMap = new Map<string, string>();
  }

  changeAnswer(answerId: string) {
    this.answerId = answerId;
  }

  changeBranch(branch: BranchChild) {
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

  checkAnswer(testId: string) {
    if (this.currentTest.type === this.testTypes.ONE_CORRECT_ANSWER) {
      this.checkOneAnswer(testId);
    } else if (this.currentTest.type === this.testTypes.SEVERAL_CORRECT_ANSWER) {
      this.checkSeveralAnswer(testId);
    } else if(this.currentTest.type === this.testTypes.MATCH) {
      this.checkMapAnswer(testId);
    }
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

  checkSeveralAnswer(testId: string) {
    this.testsService.checkAnswer({"testId": testId, "answerIds": this.answerIds})
      .subscribe((res) => {
        console.log(res);
        this.correctAnswerIds = res.correctIds;
      });
    this.needCheck = false;
  }

  private checkMapAnswer(testId: string) {
    console.log("answerMap: " , this.answerMap);
    this.testsService.checkAnswer({"testId": testId, "question2Answer": this.answerMap})
      .subscribe((res) => {
        console.log(res);
        this.correctAnswerMap = res.correctAnswerMap;
        console.log(this.correctAnswerMap);
      });
    this.needCheck = false;
  }

  normalizeText(answer: string): string {
    var lines = [];
    var line = "";
    let arr = answer.split(" ");
    arr.forEach(word => {
      if (line.length + word.length < 90) {
        line = line + " " + word
      } else {
        lines.push(line);
        line = word;
      }
    })
    if (line.length > 0) lines.push(line);
    return lines.join("\n  ");
  }

  finish() {
    this.attemptService.getResult(this.attempt.attemptId)
      .subscribe((res) => {
        console.log("RESULT " + this.attemptResult);
        this.attemptResult = res;
      });
  }

  printEvent($event: Event) {
    console.log($event);
  }
}

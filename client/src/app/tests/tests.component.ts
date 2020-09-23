import { Component, OnInit } from '@angular/core';
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
  branch: string;
  difficultLevel: string;
  tests: Test[] = [];
  currentTestIndex: number;
  currentTest: Test;
  testTypes = TestTypeEnum;
  severalCorrectAnswers: Answer[];

  constructor(private testsService: TestsService) { }

  ngOnInit(): void {
  }

  createTests(){
    this.testsService.create(this.branch, this.difficultLevel)
      .pipe(finalize(() => {
        this.currentTestIndex = 0;
        this.currentTest = this.tests[this.currentTestIndex];
      }))
      .subscribe((res: Test[]) => {
      console.log("TEST: " + res);
      this.tests = res;
    }).add();
  }

  nextTest(test: Test, answer: Answer) {
    this.testsService.answer(test, answer);
    this.currentTest = this.tests[++this.currentTestIndex];
  }

  addOrRemoveAnswer(answer: Answer) {

  }
}

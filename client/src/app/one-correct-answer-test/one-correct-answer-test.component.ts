import { Component, OnInit } from '@angular/core';
import {OneCorrectAnswerTestService} from "../service/one-correct-answer-test.service";
import {OneCorrectAnswerTest} from "../model/oneCorrectAnswerTest.model";

@Component({
  selector: 'app-one-correct-answer-test',
  templateUrl: './one-correct-answer-test.component.html',
  styleUrls: ['./one-correct-answer-test.component.css']
})
export class OneCorrectAnswerTestComponent implements OnInit {
  test: OneCorrectAnswerTest;

  constructor(private testService: OneCorrectAnswerTestService) { }

  ngOnInit(): void {
    this.getTest("awd")
  }

  getTest(id: string) {
    this.testService.getTestById(id)
      .subscribe((res: OneCorrectAnswerTest) => {
        console.log("TEST: " + res);
        this.test = res;
      });
  }

}

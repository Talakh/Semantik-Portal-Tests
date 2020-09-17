import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OneCorrectAnswerTestComponent} from "./one-correct-answer-test/one-correct-answer-test.component";

const routes: Routes = [
  { path: 'test', component: OneCorrectAnswerTestComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

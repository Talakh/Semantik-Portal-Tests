import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {OneCorrectAnswerTestComponent} from './one-correct-answer-test/one-correct-answer-test.component';
import {TokenService} from "./service/token.service";
import {OneCorrectAnswerTestService} from "./service/one-correct-answer-test.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    OneCorrectAnswerTestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule

  ],
  providers: [
    TokenService,
    OneCorrectAnswerTestService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

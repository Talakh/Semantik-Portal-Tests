import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TokenService} from "./service/token.service";
import {HttpClientModule} from "@angular/common/http";
import {TestsComponent} from './tests/tests.component';
import {FormsModule} from "@angular/forms";
import {TestsService} from "./service/tests.service";
import {AttemptService} from "./service/attempt.service";

@NgModule({
  declarations: [
    AppComponent,
    TestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    TokenService,
    TestsService,
    AttemptService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TestsComponent} from './tests/tests.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TestsService} from "./service/tests.service";
import {AttemptService} from "./service/attempt.service";
import {BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import {BasicAuthHtppInterceptorService} from "./service/basic-auth-interceptor.service";
import {MatButtonModule} from "@angular/material/button";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { DifficultLevelComponent } from './difficult-level/difficult-level.component';
import {DifficultLevelService} from "./service/difficult-level.service";

@NgModule({
  declarations: [
    AppComponent,
    TestsComponent,
    LoginComponent,
    LogoutComponent,
    UserComponent,
    AddUserComponent,
    HeaderComponent,
    FooterComponent,
    DifficultLevelComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        MatTableModule,
        MatButtonModule,
        MatCheckboxModule,
        MatSelectModule,
        MatRadioModule,
        MatProgressBarModule
    ],
  providers: [
    TestsService,
    AttemptService,
    DifficultLevelService,
    [ { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }],
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

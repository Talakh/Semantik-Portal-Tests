import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TestsComponent} from "./tests/tests.component";
import {AuthGaurdService} from "./service/auth-gaurd.service";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";
import {AddUserComponent} from "./add-user/add-user.component";

const routes: Routes = [
  { path: 'tests', component: TestsComponent },
  { path: '', component: UserComponent,canActivate:[AuthGaurdService] },
  { path: 'addUser', component: AddUserComponent,canActivate:[AuthGaurdService]},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

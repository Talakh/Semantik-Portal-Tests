import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {User, HttpClientService} from "../service/httpclient.service";

@Component({
  selector: "app-add-employee",
  templateUrl: "./add-user.component.html",
  styleUrls: ["./add-user.component.css"]
})
export class AddUserComponent implements OnInit {
  user: User = new User("", "", "", "");

  constructor(private httpClientService: HttpClientService<User>,
    private router: Router) {}

  ngOnInit() {}

  createUser(): void {
    this.httpClientService.createUser(this.user).subscribe(data => {
      alert("Employee created successfully.");
      this.router.navigate([''])
    });
  }
}

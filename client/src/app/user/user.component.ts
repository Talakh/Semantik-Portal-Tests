import { Component, OnInit } from "@angular/core";
import { HttpClientService, User } from '../service/httpclient.service';

@Component({
  selector: "app-employee",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.css"]
})
export class UserComponent implements OnInit {
  employees: User[];
  displayedColumns: string[] = ["name", "designation", "delete"];

  constructor(private httpClientService: HttpClientService<User>) {}

  ngOnInit() {
    this.httpClientService
      .getEmployees()
      .subscribe(response => this.handleSuccessfulResponse(response));
  }

  handleSuccessfulResponse(response) {
    this.employees = response;
  }

  deleteEmployee(user: User): void {
    this.httpClientService.deleteEmployee(user).subscribe(data => {
      this.employees = this.employees.filter(u => u !== user);
    });
  }
}

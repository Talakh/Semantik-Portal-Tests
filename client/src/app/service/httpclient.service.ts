import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {Observable} from "rxjs";

export class User {
  constructor(
    public empId: string,
    public name: string,
    public designation: string,
    public salary: string
  ) {}
}

@Injectable({
  providedIn: "root"
})
export class HttpClientService<T> {
  constructor(private httpClient: HttpClient) {}

  getEmployees() {
    return this.httpClient.get<User[]>("http://localhost:8081/employees");
  }

  public deleteEmployee(employee) {
    return this.httpClient.delete<User>(
      "http://localhost:8081/employees" + "/" + employee.empId
    );
  }

  public createUser(user) {
    console.log(user)
    return this.httpClient.post<User>(
      "http://localhost:8081/employees",
      user
    );
  }

  post(url: string, entity: any): Observable<T> {
    return this.httpClient.post<T>(url, entity);
  }

  get<T>(url: string): Observable<T> {
    return this.httpClient.get<T>(url);
  }

  put(url: string, entity: any): Observable<any> {
    return this.httpClient.put<any>(url, entity);
  }

  delete(url: string) {
    return this.httpClient.delete<T>(url);
  }

}

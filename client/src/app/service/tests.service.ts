import {Injectable} from "@angular/core";
import {Test} from "../model/test.model";
import {Observable} from "rxjs";
import {CheckAnswer} from "../model/check-answer.model";
import {BranchChild} from "../model/branch-child.model";
import {HttpClientService} from "./httpclient.service";

const url = 'http://localhost:8081/api/v1/admin/test';

@Injectable()
export class TestsService {
  constructor(private httpClientService: HttpClientService<Test[]>) {
  }

  checkAnswer(entity: any): Observable<CheckAnswer> {
    return this.httpClientService.put(`${url}/check`, entity);
  }

  branches(): Observable<BranchChild[]> {
    return this.httpClientService.get(`${url}/branches`);
  }
}

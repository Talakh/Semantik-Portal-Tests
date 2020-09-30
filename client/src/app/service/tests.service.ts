import {Injectable} from "@angular/core";
import {TokenService} from "./token.service";
import {Test} from "../model/test.model";
import {Observable} from "rxjs";
import {CheckAnswer} from "../model/check-answer.model";
import {BranchChild} from "../model/branch-child.model";

const url = 'http://localhost:8081/api/v1/admin/test';

@Injectable()
export class TestsService {
  constructor(private tokenService: TokenService<Test[]>) {
  }

  checkAnswer(entity: any): Observable<CheckAnswer> {
    return this.tokenService.put(`${url}/check`, entity);
  }

  branches(): Observable<BranchChild[]> {
    return this.tokenService.get(`${url}/branches`);
  }
}

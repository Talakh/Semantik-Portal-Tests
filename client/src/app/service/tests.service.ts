import {Injectable} from "@angular/core";
import {TokenService} from "./token.service";
import {Test} from "../model/test.model";
import {Observable} from "rxjs";
import {CheckAnswer} from "../model/check-answer.model";

const url = 'http://localhost:8081/api/v1/admin/test';

@Injectable()
export class TestsService {
  constructor(private tokenService: TokenService<Test[]>) {
  }

  create(branch: string, difficultLevel: string): Observable<Test[]> {
    console.log('create test' + branch + ' ' + difficultLevel);
    return this.tokenService.post(`${url}/create`, {"branch": branch, "difficultLevel": difficultLevel})
  }

  checkAnswer(entity: any): Observable<CheckAnswer> {
    return this.tokenService.put(`${url}/check`, entity);
  }
}

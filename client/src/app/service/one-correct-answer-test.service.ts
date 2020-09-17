import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {TokenService} from "./token.service";
import {OneCorrectAnswerTest} from "../model/oneCorrectAnswerTest.model";

const url = 'http://localhost:8081/api/v1/admin/test';

@Injectable()
export class OneCorrectAnswerTestService {
  constructor(private tokenService: TokenService<OneCorrectAnswerTest>) {
  }

  getTestById(id: string): Observable<OneCorrectAnswerTest> {
    console.log('get Test');
    return this.tokenService.get(`${url}/${id}`)
  }
}

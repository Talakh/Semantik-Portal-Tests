import {Injectable} from "@angular/core";
import {TokenService} from "./token.service";
import {Test} from "../model/test.model";
import {Observable} from "rxjs";
import {Answer} from "../model/answer.model";

const url = 'http://localhost:8081/api/v1/admin/test';

@Injectable()
export class TestsService {
  constructor(private tokenService: TokenService<Test[]>) {
  }

  create(branch: string, difficultLevel: string): Observable<Test[]> {
    console.log('create test' + branch + ' ' + difficultLevel);
    return this.tokenService.post(`${url}/create`, {"branch": branch, "difficultLevel": difficultLevel})
  }

  answer(test:Test, answer: Answer) {
    this.tokenService.put(`${url}/answer`, {"test": test, "answer": answer})
      .subscribe((res) => console.log(res));
  }
}

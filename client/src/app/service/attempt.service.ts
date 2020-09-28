import {Injectable} from "@angular/core";
import {TokenService} from "./token.service";
import {Observable} from "rxjs";
import {Attempt} from "../model/attempt.model";

const url = 'http://localhost:8081/api/v1/admin/attempts';

@Injectable()
export class AttemptService {
  constructor(private tokenService: TokenService<Attempt>) {
  }

  create(branch: string, difficultLevel: string): Observable<Attempt> {
    console.log('create test' + branch + ' ' + difficultLevel);
    return this.tokenService.post(`${url}`, {"branch": branch, "difficultLevel": difficultLevel})
  }
}

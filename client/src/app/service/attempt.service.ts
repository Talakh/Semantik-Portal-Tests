import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Attempt} from "../model/attempt.model";
import {AttemptResult} from "../model/attempt-result.model";
import {BranchChild} from "../model/branch-child.model";
import {HttpClientService} from "./httpclient.service";

const url = 'http://localhost:8081/api/v1/admin/attempts';

@Injectable()
export class AttemptService {
  constructor(private httpClientService: HttpClientService<Attempt>) {
  }

  create(branch: BranchChild, difficultLevel: string): Observable<Attempt> {
    console.log('create test' + branch + ' ' + difficultLevel);
    return this.httpClientService.post(`${url}`, {"branch": branch, "difficultLevel": difficultLevel})
  }

  getResult(id: string): Observable<AttemptResult> {
    return this.httpClientService.get(`${url}/${id}`);
  }
}

import {Injectable} from "@angular/core";
import {HttpClientService} from "./httpclient.service";
import {Test} from "../model/test.model";
import {Observable} from "rxjs";
import {BranchChild} from "../model/branch-child.model";
import {DifficultLevel} from "../model/difficult-level.model";
import {ThesesClass} from "../model/theses-class.model";

const url = 'http://localhost:8081/api/v1/admin/testDifficultLevels';

@Injectable()
export class DifficultLevelService {
  constructor(private httpClientService: HttpClientService<Test[]>) {
  }

  getDifficultLevels(): Observable<DifficultLevel[]> {
    return this.httpClientService.get(url);
  }

  updateDifficultLevel(entity: any) {
    this.httpClientService.put(url, entity)
      .subscribe(res => console.log(res));
  }

  getTheseClasses(): Observable<ThesesClass[]> {
    return this.httpClientService.get(`${url}/theseClasses`);
  }
}

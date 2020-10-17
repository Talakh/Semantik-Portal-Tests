import { Component, OnInit } from '@angular/core';
import {DifficultLevelService} from "../service/difficult-level.service";
import {DifficultLevel} from "../model/difficult-level.model";
import {ThesesClass} from "../model/theses-class.model";

@Component({
  selector: 'app-difficult-level',
  templateUrl: './difficult-level.component.html',
  styleUrls: ['./difficult-level.component.scss']
})
export class DifficultLevelComponent implements OnInit {
  levels: DifficultLevel[] = [];
  theseClasses: ThesesClass[] = [];

  constructor(private difficultLevelService: DifficultLevelService) { }

  ngOnInit(): void {
    this.difficultLevelService.getDifficultLevels().subscribe(res => this.levels = res);
    this.difficultLevelService.getTheseClasses().subscribe(res => {
      this.theseClasses = res;
      console.log(res);
    });

  }

}

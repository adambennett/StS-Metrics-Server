import { Component, OnInit } from '@angular/core';
import {TopService} from "../services/topservice/top.service";

@Component({
  selector: 'app-run-list',
  templateUrl: './run-list.component.html',
  styleUrls: ['./run-list.component.scss']
})
export class RunListComponent implements OnInit {

  runs: Array<any>;
  dataSource: any[] = [];
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];

  constructor(private topService: TopService) { }

  ngOnInit(): void { this.topService.getBundles().subscribe(data => {
    this.runs = data;
    this.dataSource = data;
  });}

}

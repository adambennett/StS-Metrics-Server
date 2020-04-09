import {Component, OnInit, ViewChild} from '@angular/core';
import {TopService} from "../services/topservice/top.service";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-run-list',
  templateUrl: './run-list.component.html',
  styleUrls: ['./run-list.component.scss']
})
export class RunListComponent implements OnInit {
  displayedColumns: string[] = ['host', 'deck', 'ascension', 'challenge', 'victory', 'killedBy', 'floor', 'kaiba'];
  runs: Array<any>;
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private topService: TopService) { }

  ngOnInit(): void {
    this.topService.getBundles().subscribe(data => {
      this.runs = data;
      this.dataSource = new MatTableDataSource<any>(this.runs.slice().reverse());
      this.dataSource.paginator = this.paginator;
    });
  }

}



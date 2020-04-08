import {Component, OnInit, ViewChild} from '@angular/core';
import {TopService} from "../services/topservice/top.service";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {Observable} from "rxjs";
import {ArrayPage} from '@hallysonh/pageable';

@Component({
  selector: 'app-run-list',
  templateUrl: './run-list.component.html',
  styleUrls: ['./run-list.component.scss']
})
export class RunListComponent implements OnInit {
  displayedColumns: string[] = ['host', 'starting_deck', 'ascension_level', 'challenge_level', 'victory', 'killed_by', 'chose_seed', 'floor_reached', 'playing_as_kaiba'];
  runs: Observable<any>;
  pages: ArrayPage<any>;
  totalpages: number;
  dataSource = [];
  activePageDataChunk = [];
  length = 1000;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 50, 100, 1000];
  pageEvent: PageEvent;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private topService: TopService) { }

  ngOnInit(): void {
    //this.topService.getBundles().subscribe(data => { this.runs = data; });
    this.runs = this.topService.getBundlePages(0, 5);
    //this.dataSource = this.runs;
    //this.activePageDataChunk = this.dataSource.slice(0, this.pageSize);
    this.topService.getNumberOfBundles().subscribe(data => { this.totalpages = data; })
    console.log("ELEMENTS: "+ this.totalpages);
    console.log("PAGEABLE PAGE 0: " + this.runs);
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }

  onPageChanged(e) {
    let firstCut = e.pageIndex * e.pageSize;
    let secondCut = firstCut + e.pageSize;
    //this.activePageDataChunk = this.dataSource.slice(firstCut, secondCut);
  }

}

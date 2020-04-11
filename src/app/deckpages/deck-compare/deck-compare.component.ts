import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {TopService} from "../../services/topservice/top.service";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-deck-compare',
  templateUrl: './deck-compare.component.html',
  styleUrls: ['./deck-compare.component.scss']
})
export class DeckCompareComponent implements OnInit {
  displayedColumns: string[] = ['deck', 'runs', 'wins', 'a20runs', 'a20wins', 'c20runs', 'c20wins', 'floor', 'killed', 'kaiba'];
  cards: Array<any>;
  dataSource: MatTableDataSource<any>;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private topService: TopService) { }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit(): void {
    this.topService.getDeckCompare().subscribe(data => {
      this.cards = data;
      this.dataSource = new MatTableDataSource<any>(this.cards);
      this.dataSource.sort = this.sort;
    });
  }

}

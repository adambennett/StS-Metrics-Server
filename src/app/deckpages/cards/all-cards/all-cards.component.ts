import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {TopService} from "../../../services/topservice/top.service";

@Component({
  selector: 'app-all-cards',
  templateUrl: './all-cards.component.html',
  styleUrls: ['./all-cards.component.scss']
})
export class AllCardsComponent implements OnInit {
  //displayedColumns: string[] = ['name', 'pop', 'power', 'offered', 'picked', 'pickVic', 'id'];
  displayedColumns: string[] = ['name', 'pop', 'power', 'offered', 'picked', 'pickVic'];
  cards: Array<any>;
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private topService: TopService) { }

  ngOnInit(): void {
    this.topService.getCards().subscribe(data => {
      this.cards = data;
      this.dataSource = new MatTableDataSource<any>(this.cards);
      this.dataSource.paginator = this.paginator;
    });
  }

}

import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {TopService} from "../../../services/topservice/top.service";

@Component({
  selector: 'app-all-relics',
  templateUrl: './all-relics.component.html',
  styleUrls: ['./all-relics.component.scss']
})
export class AllRelicsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'popularity', 'power', 'picked', 'pickVic'];
  relics: Array<any>;
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private topService: TopService) { }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.topService.getRelics().subscribe(data => {
      this.relics = data;
      this.dataSource = new MatTableDataSource<any>(this.relics);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

}

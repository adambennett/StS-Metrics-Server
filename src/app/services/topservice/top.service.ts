import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TopService {

  public API = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getCards(): Observable<any> {
    return this.http.get(this.API + 'Cards');
  }

  getBundles(): Observable<any> {
    return this.http.get(this.API + 'Runs');
  }

  get(id: number) {
    return this.http.get(this.API + 'Runs/' + id);
  }

  getNumberOfBundles(): Observable<number> {
    return this.http.get<number>(this.API + 'Runs/number');
  }

  getBundle(id: number) {
    return this.http.get(this.API + 'Runs/' + id);
  }

  getBundlePages(pageNum: number, pageSize: number): Observable<any> {
    return this.http.get(this.API + 'Runs/pages?page=' + pageNum + '&size=' + pageSize);
  }

  save(bundle: any, id: number): Observable<any> {
    let result: Observable<any>;
    if (bundle.href) {
      result = this.http.put(bundle.href, bundle);
    } else {
      result = this.http.post(this.API + 'Runs/', bundle);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}

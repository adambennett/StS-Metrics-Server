import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BundleService {

  public API = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getBundles(): Observable<any> {
    return this.http.get(this.API + 'Betrics');
  }

  get(id: number) {
    return this.http.get(this.API + 'Betrics/' + id);
  }

  getBoard(id: number) {
    return this.http.get('http://localhost:8080/Bundles/' + id);
  }

  save(bundle: any, id: number): Observable<any> {
    let result: Observable<any>;
    if (bundle.href) {
      result = this.http.put(bundle.href, bundle);
    } else {
      result = this.http.post(this.API + 'Bundles/', bundle);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}

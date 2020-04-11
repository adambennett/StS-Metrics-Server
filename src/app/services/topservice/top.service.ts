import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TopService {

  public API = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  getDeckCompare(): Observable<any> {
    return this.http.get(this.API + 'Decks');
  }

  getDeckInfo(deck: string): Observable<any> {
    return this.http.get(this.API + 'Decks/' + deck + "/info");
  }

  getCards(): Observable<any> {
    return this.http.get(this.API + 'Cards');
  }

  getRelics(): Observable<any> {
    return this.http.get(this.API + 'Relics');
  }

  getPotions(): Observable<any> {
    return this.http.get(this.API + 'Potions');
  }

  getNeows(): Observable<any> {
    return this.http.get(this.API + 'Neow');
  }

  getCardsFromDeck(deck: string): Observable<any> {
    return this.http.get(this.API + 'Cards/' + deck);
  }

  getRelicsFromDeck(deck: string): Observable<any> {
    return this.http.get(this.API + 'Relics/' + deck);
  }

  getPotionsFromDeck(deck: string): Observable<any> {
    return this.http.get(this.API + 'Potions/' + deck);
  }

  getNeowsFromDeck(deck: string): Observable<any> {
    return this.http.get(this.API + 'Neow/' + deck);
  }

  getRuns(): Observable<any> {
    return this.http.get(this.API + 'Runs');
  }
}

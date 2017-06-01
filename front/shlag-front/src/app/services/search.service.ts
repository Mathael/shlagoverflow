import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class SearchService {
  
  
  
  private headers = new Headers({ 'Content-Type': 'application/json', 'Accept' : 'application/json' });
  private options = new RequestOptions({ headers: this.headers });
  constructor(private http: Http) {}

  /**
   * 
   * Return : Array of Questions
   */
  do(query: string) {
    return this
            .http
            .post('/api/search', {query: query}, this.options)
            .debounceTime(800)
            .map(res => res.json())
            .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

}

import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs';

@Injectable()
export class SearchService {

    private headers = new Headers({'Content-Type': 'application/json', 'Accept': 'application/json'});
    private options = new RequestOptions({headers: this.headers});

    constructor(private http: Http) {}

    /**
     * Return : Array of Questions
     */
    do(query: string) {
        return this
            .http
            .get(`/api/search/${query}`, this.options)
            .debounceTime(1500)
            .map(res => res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }
}

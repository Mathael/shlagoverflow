import {Question} from './model/question';
import {SearchService} from './services/search.service';
import {Component} from '@angular/core';
import {Subject} from 'rxjs';
@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'app';
    searchTerm: Subject<string> = new Subject<string>();
    results: any[] = [];

    constructor(private search: SearchService) {}

    doSearch(query: string) {
        this.search.do(query).subscribe(res => {
            this.results = res;
        })
    }
}

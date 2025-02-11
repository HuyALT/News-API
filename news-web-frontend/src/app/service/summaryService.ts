import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { summaryData } from '../models/summaryData';



@Injectable({
  providedIn: 'root'
})
export class SummaryService{
    constructor(private http: HttpClient){}

    getSummary(datainput: string): Observable<summaryData>{
        const url  = "http://127.0.0.1:8888/api/summary"
        return this.http.post<summaryData>(url,{
            contents: datainput
        })
    }
}
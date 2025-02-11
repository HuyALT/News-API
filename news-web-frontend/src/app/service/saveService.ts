import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SaveNews } from '../models/save';

@Injectable({
  providedIn: 'root'
})
export class SaveService{

    constructor(private http: HttpClient){}

    getAllSave(): Observable<SaveNews[]> {
        const url = 'http://localhost:8080/api/v1/user/saved'
        return this.http.get<SaveNews[]>(url, {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }
    addSaved(newsid: number): Observable<SaveNews> {
        const url = 'http://localhost:8080/api/v1/user/saved'
        const params = new HttpParams().set('newsid', newsid)
        return this.http.post<SaveNews>(url,'', {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        }), params: params})
    }
    deleteSaved(savedid: number): Observable<any>{
        const url = 'http://localhost:8080/api/v1/user/saved'
        const params = new HttpParams().set('id', savedid)
        return this.http.delete(url,{headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        }), params: params, responseType:'text'})
    }
}
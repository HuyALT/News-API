import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, combineLatest } from 'rxjs';
import { NewsComment } from '../models/comment';

@Injectable({
  providedIn: 'root'
})

export class CommentService{

    constructor(private http: HttpClient){ }

    getCommentByNewid(newsid: number): Observable<NewsComment[]>{
        const param_newsid = new HttpParams().set('newsid', newsid)
        const url = "http://localhost:8080/api/v1/comments"
        return this.http.get<NewsComment[]>(url, {params: param_newsid})
    }
    addComment(newsid: number, content: string): Observable<NewsComment>{
      const url = "http://localhost:8080/api/v1/user/comments"
      const data: any = {
        newsId: newsid,
        content: content
      }
      return this.http.post<NewsComment>(url, data, {headers: new HttpHeaders({
        'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
      })})
    }
}
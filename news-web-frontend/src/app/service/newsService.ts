import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { news } from '../models/news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

    private BASE_URL: string = 'http://localhost:8080/api/v1/news/latest-news'
    
    constructor(private http: HttpClient){ }

    getAllNews(): Observable<news[]>{
        
      return this.http.get<news[]>(this.BASE_URL);
    }

    getByCategory(categoryid: string): Observable<news[]>{
      const param_category = new HttpParams().set('categoryid', categoryid)
      return this.http.get<news[]>(this.BASE_URL, {params: param_category});
        
    }

    getBySubcategory(subcategoryid: string): Observable<news[]>{
      const param_subcategory = new HttpParams().set('subcategoryid', subcategoryid)
      return this.http.get<news[]>(this.BASE_URL, {params: param_subcategory})
    }

    getDetail(id: number): Observable<news> {
      const url = "http://localhost:8080/api/v1/news/"+id
      return this.http.get<news>(url)
    }
    searchByKeyword(keyword: string) : Observable<news[]> {
      const url = 'http://localhost:8888/api/news/search'
      const params = new HttpParams().set('keyword', keyword)
      return this.http.get<news[]>(url, {params: params})
    }
}
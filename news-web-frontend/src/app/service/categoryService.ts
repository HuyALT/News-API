import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { subcategory } from '../models/subcategory';
import { Category } from '../models/category';
import { url } from 'inspector';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

    constructor(private http: HttpClient) {  }

    getAllcategory(): Observable<Category[]>{
        const url = "http://localhost:8080/api/v1/category";
        return this.http.get<Category[]>(url);
    }

    getSubcategory(categoryid: number): Observable<subcategory[]>{
        const param_1 = new HttpParams().set('categoryid', categoryid);
        const url = "http://localhost:8080/api/v1/subcategory/category";
        return this.http.get<subcategory[]>(url, {params:param_1});
    }

    getCategoryById(categoryid: number): Observable<Category> {
        const url = "http://localhost:8080/api/v1/category/"+categoryid
        return this.http.get<Category>(url)
    }

    getSubcategoryById(subcategoryid: number): Observable<subcategory>{
        const url = "http://localhost:8080/api/v1/subcategory/"+subcategoryid
        return this.http.get<subcategory>(url)
    }
    getAllSubcategory(): Observable<subcategory[]>{
        const url = 'http://localhost:8080/api/v1/subcategory'
        return this.http.get<subcategory[]>(url)
    }
  
}


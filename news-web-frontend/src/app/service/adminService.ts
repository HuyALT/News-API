import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { news } from '../models/news';
import { Category } from '../models/category';
import { subcategory } from '../models/subcategory';


@Injectable({
  providedIn: 'root'
})

export class AdminService {

    constructor(private http: HttpClient) { }

    getAllUser(): Observable<User[]>{
        const url = "http://localhost:8080/api/v1/admin/user"
        return this.http.get<User[]>(url,{headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })
        })
    }

    changeLocked(userid: number):Observable<any>{
        const url = "http://localhost:8080/api/v1/admin/user/"+userid
        return this.http.put(url, '', {
            headers: new HttpHeaders({
                'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
            }), responseType: 'text'
        })
    }

    getAllNews(): Observable<news[]> {
        const url = "http://localhost:8080/api/v1/admin/news/latest-news"
        return this.http.get<news[]>(url, 
            {headers: new HttpHeaders({
                'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
            })
            })
    }

    addNews(newslatest:news): Observable<news> {
        const url = 'http://localhost:8080/api/v1/admin/news/add'
        return this.http.post<news>(url, newslatest,{ headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
      }
    updateNews(newsUpdate: news, newsid: number): Observable<news> {
        const url = 'http://localhost:8080/api/v1/admin/news/update/'+newsid
        return this.http.put<news>(url, newsUpdate, {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }
    hideandshow(newsid: number): Observable<any>{
        const url = 'http://localhost:8080/api/v1/admin/news/changeActive/'+newsid
        return this.http.put(url, '',{headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        }), responseType: 'text'})
    }
    addCategory(categoryAdd: Category): Observable<Category>{
        const url = 'http://localhost:8080/api/v1/admin/category/add'
        return this.http.post<Category>(url,categoryAdd,{headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }

    updateCategory(categoryid: number, categoryupdate: Category): Observable<Category> {
        const url = 'http://localhost:8080/api/v1/admin/category/update/'+categoryid
        return this.http.put<Category>(url, categoryupdate, {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }
    deleteCategory(categoryid: number): Observable<any> {
        const url = 'http://localhost:8080/api/v1/admin/category/'+categoryid
        return this.http.delete(url, {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        }), responseType: 'text'})
    }
    updateSubcategory(subcategoryid: number, subcatehoryupdate: subcategory): Observable<subcategory>{
        const url = 'http://localhost:8080/api/v1/admin/subcategory/update/'+subcategoryid
        return this.http.put<subcategory>(url, subcatehoryupdate, {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }
    addSubcategory(subcategoryadd: subcategory): Observable<subcategory>{
        const url = 'http://localhost:8080/api/v1/admin/subcategory/add'
        return this.http.post<subcategory>(url, subcategoryadd, {headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }
    deleteSubcategory(subcategoryid: number): Observable<any> {
        const url = 'http://localhost:8080/api/v1/admin/subcategory/'+subcategoryid
        return this.http.delete(url,{headers: new HttpHeaders({
            'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }
}
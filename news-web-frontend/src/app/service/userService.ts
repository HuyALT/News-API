import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { login } from '../models/login';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})

export class UserService {
    constructor(private http: HttpClient){ }

    hostname: string = 'http://localhost:8080'
    getUserInfo(): Observable<User>{
         
        const url = this.hostname+"/api/v1/account/setting/getinfo"

        return this.http.get<User>(url, {headers: new HttpHeaders({
          'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
        })})
    }

    getUserById(userId: number): Observable<User> {
      const url = this.hostname+"/api/v1/account/setting/another"
      const paramUserid = new HttpParams().set('userId',userId)

      return this.http.get<User>(url, {params: paramUserid})
    }

    changePassword(oldpassword: string, newpassword: string): Observable<any>{
      const url = this.hostname+"/api/v1/account/setting/changePassword"
      const body: any = {
        oldPassword: oldpassword,
        newPassword: newpassword
      }
      return this.http.post(url, body, {headers: new HttpHeaders({
        'Authorization': 'Bearer '+window.sessionStorage.getItem('localhost:4200/Jwt-Token')
      }),responseType: 'text'})
      
    }
    
}
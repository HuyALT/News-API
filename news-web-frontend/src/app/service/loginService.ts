import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { login } from '../models/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient){ }

    loginSubmit(loginForm: login): Observable<string>{
        const url = "http://localhost:8080/api/v1/auth/login"
        return this.http.post(url, loginForm, {responseType: 'text'})
        
    }

   
  
}
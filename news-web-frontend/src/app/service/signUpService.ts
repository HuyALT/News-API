import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Singup } from '../models/singnup';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

    constructor(private http: HttpClient){ }

    signUpAccount(signupForm: Singup): Observable<any> {
        const url = "http://localhost:8080/api/v1/auth/register"
        return this.http.post(url, signupForm)
    }

    sendOtp(email: string): Observable<any> {
        const url = "http://localhost:8080/api/v1/auth/otp/send-otp"
        const httpparams = new HttpParams().set('email', email)
        return this.http.post(url, '',{params: httpparams, responseType: 'text'})
    }

    verifyOtp(email: string, otp: string): Observable<any>{
        const url = "http://localhost:8080/api/v1/auth/otp/verify-otp"
        const httpparams = new HttpParams().set('email', email).set('otp', otp)

        return this.http.post(url, '', {params: httpparams, responseType: 'text'})
    }
}
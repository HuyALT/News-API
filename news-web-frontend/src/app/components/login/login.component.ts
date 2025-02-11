import { Component, OnInit } from '@angular/core';
import { login } from '../../models/login';
import { LoginService } from '../../service/loginService';
import { error } from 'console';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../../service/userService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm: login={username: '', password: ''}; 

  isloginFail: boolean = false;

  jwtToken: string = ''

  constructor(private loginservice: LoginService, private userservice: UserService, private router: Router, private location: Location){ }

  ngOnInit(): void {
    this.checkLogin()
    
  }

  loginSubmit(){
      this.loginservice.loginSubmit(this.loginForm).subscribe({
        next: data=>{
          this.jwtToken = data
          window.sessionStorage.setItem('localhost:4200/Jwt-Token', this.jwtToken)
          this.userservice.getUserInfo().subscribe({
            next: data=>{
              if (data.role==2){
                this.location.back()
              }
              else{
                this.router.navigate(['/accountinfo'])
              }
            }
          })
        },
        error: e=>{
          if (e.status==403){
            this.isloginFail = true
          }
        }
      })
  }

  checkLogin() {
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.location.back()
    }
  }
  signUp(){
    this.router.navigate(['/signup'])
  }
}

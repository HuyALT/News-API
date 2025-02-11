import { Component, OnDestroy, OnInit } from '@angular/core';
import { timer } from 'rxjs';
import { SignUpService } from '../../service/signUpService';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';
import { Singup } from '../../models/singnup';
import { LoginService } from '../../service/loginService';
import { login } from '../../models/login';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrl: './verify-otp.component.css'
})
export class VerifyOTPComponent implements OnInit {

  inputOTP: string = ''
  buttonDisabled: boolean = false
  
  isnonValid: boolean = false
  info: string = ''
  signUpForm: Singup = {
    username: '',
    password: '',
    email: ''
  }

  constructor(private signupservice: SignUpService, private loginservice: LoginService, private router: Router, private route: ActivatedRoute){
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      email: string;
      username: string;
      password: string;
    };
    if (state!=null) {
      this.signUpForm.email = state.email;
      this.signUpForm.username = state.username;
      this.signUpForm.password = state.password;
    }
   }

  ngOnInit() {
    console.log(this.signUpForm)
  }


  validateOTP() {
    this.signupservice.verifyOtp(this.signUpForm.email, this.inputOTP).subscribe({
      next: data=>{
        this.isnonValid = false
        this.info = 'Mã OTP hợp lệ đợi về trang chủ trong 3s'
        timer(3000).subscribe(()=>{
          const loginForm: login = {
            username: this.signUpForm.username,
            password: this.signUpForm.password
          }
          this.loginservice.loginSubmit(loginForm).subscribe(data=>{
          window.sessionStorage.setItem('localhost:4200/Jwt-Token', data)
          this.router.navigate(['/home'])
        })
      })
      },
      error: e=>{
        if (e.status==403){
          this.isnonValid = true
          this.info = 'Mã OTP không chính xác'
        }
      }
    })
  }

  resendOTP() {
    this.buttonDisabled = true;
    timer(30000).subscribe(() => {
      this.buttonDisabled = false;
    });
    this.signupservice.sendOtp(this.signUpForm.email)
  }
}

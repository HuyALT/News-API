import { Component, OnInit } from '@angular/core';
import { Singup } from '../../models/singnup';
import { SignUpService } from '../../service/signUpService';
import { error } from 'console';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit{

  signupForm: Singup = {
    username: '',
    password: '',
    email: ''
  }

  confirmpassword: string='';
  isError: boolean = false
  errorText: string = ''

  constructor(private signUpservice: SignUpService, private router: Router, private location: Location){}

  ngOnInit(): void {
    
  }

  onSubmit(){
    if (this.signupForm.password!=this.confirmpassword){
      this.isError = true
      this.errorText = 'Mật khẩu xác nhận không đúng'
    }
    else {
      this.signUpservice.signUpAccount(this.signupForm).subscribe(data=>{
        this.signUpservice.sendOtp(this.signupForm.email).subscribe(an=>{
          this.router.navigate(['/verify-OTP'], {
            state: {
              email: this.signupForm.email,
              username: this.signupForm.username,
              password: this.signupForm.password
            }
          });
        })
      }, error=>{
        if (error.status==400) {
          this.isError = true
          this.errorText = 'Username hoặc email đã tồn tại'
        }
      })
    }
  }
}

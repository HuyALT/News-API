import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/userService';
import { Router } from '@angular/router';
import { User } from '../../models/user';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent implements OnInit {

  user: User = {
    id: 0,
    username: '',
    image: '',
    role: 0,
    email: '',
    active: -1,
    locked: -1
  }

  oldPassword: string = ''
  newPassword: string = ''
  isError: boolean  = false
  errorText: string = ''
  passwordConfirm =''

  constructor(private userservice: UserService, private router: Router){}

  ngOnInit(): void {
    this.getAccountInfo()
  }
  
  OnSubmit(){
    if (this.newPassword != this.passwordConfirm) {
      this.isError = true
      this.errorText = 'Xác nhận mật khẩu không đúng'
    } if (this.oldPassword==''&&this.newPassword==''&&this.passwordConfirm==''){
      this.isError = true
      this.errorText = 'Vui lòng điền đầy đủ thông tin'
    }
    else{
      this.changePassword()
    }
  }

  changePassword() {
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.userservice.changePassword(this.oldPassword, this.newPassword).subscribe({
        next: data=>{
          alert('Đổi mật khẩu thành công')
          this.router.navigate(['/accountinfo'])
        },
        error: e=>{
          if (e.status==400){
            this.isError = true
            this.errorText = 'mật khẩu cũ không đúng'
          }
        }
      })
    }
  }
  
  getAccountInfo(){
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this,this.userservice.getUserInfo().subscribe({
        next: data=>{
          this.user = data
        }
      })
    }
  }

}

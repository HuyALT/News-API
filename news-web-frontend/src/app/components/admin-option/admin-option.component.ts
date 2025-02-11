import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/userService';
import { Router } from '@angular/router';
import { User } from '../../models/user';

@Component({
  selector: 'app-admin-option',
  templateUrl: './admin-option.component.html',
  styleUrl: './admin-option.component.css'
})
export class AdminOptionComponent implements OnInit{

  userInfo: User = {
    email: '',
    id: 0,
    image: '',
    role:0,
    username: '',
    locked: -1,
    active: -1
  }

  constructor(private userservice: UserService, private router: Router){ }

  ngOnInit(): void {
    this.checkAuth()
  }

  checkAuth() {
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.userservice.getUserInfo().subscribe({
        next: data=>{
          if (data.id!=1){
            this.router.navigate(['/forbidden'])
          } else {
            this.userInfo = data
          }
        },
        error: e=>{
          if (e.status==403) {
            this.router.navigate(['/forbidden'])
          }
        }
      })
    }
    else {
      this.router.navigate(['/forbidden'])
    }
  }
  logout() {
    window.sessionStorage.removeItem('localhost:4200/Jwt-Token')
    
    this.router.navigate(['/home'])
  }

  usermanager(){
    this.router.navigate(['/usermanager'])
  }
  newsmanager(){
    this.router.navigate(['/newsmanager'])
  }
  categorymanager(){
    this.router.navigate(['/categorymanager'])
  }
  subcategorymanager(){
    this.router.navigate(['/subcategorymanager'])
  }
  info(){
    this.router.navigate(['/accountinfo'])
  }
}

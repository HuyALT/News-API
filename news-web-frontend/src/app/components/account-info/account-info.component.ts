import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/userService';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrl: './account-info.component.css'
})
export class AccountInfoComponent implements OnInit {

  user: User = {
    id: 0,
    username: '',
    image: '',
    role: 0,
    email: '',
    active: -1,
    locked: -1
  }

  constructor(private userservice: UserService, private router: Router){}

  ngOnInit(): void {
    this.getUserInfo()
  }

  getUserInfo() {
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.userservice.getUserInfo().subscribe(data=> this.user =data)
    }
    else {
      this.router.navigate(['/forbidden'])
    }
  }

  changePassword(){
    this.router.navigate(['/changePassword'])
  }
}

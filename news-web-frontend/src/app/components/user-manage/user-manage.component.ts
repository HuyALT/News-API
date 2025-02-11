import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { AdminService } from '../../service/adminService';

@Component({
  selector: 'app-user-manage',
  templateUrl: './user-manage.component.html',
  styleUrl: './user-manage.component.css'
})
export class UserManageComponent implements OnInit {

  userlist: User[] =[]

  constructor(private adminservice: AdminService){ }

  ngOnInit(): void {
    this.getAlluser()
    
  }

  changeLocked(id: number){
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.adminservice.changeLocked(id).subscribe({
        next: data=>{
            this.getAlluser()
        },
        error: e=>{
          if (e.status==400) {
            alert('khóa thất bại')
          }
        }
      })
    }
  }

  getAlluser(){
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.adminservice.getAllUser().subscribe({
        next: data=>this.userlist=data
      })
    }
  }
}

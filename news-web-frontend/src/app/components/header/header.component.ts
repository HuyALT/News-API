import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/categoryService';
import { Category } from '../../models/category';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { User } from '../../models/user';
import { UserService } from '../../service/userService';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})


export class HeaderComponent implements OnInit {

  categories: Category[]=[];
  isLogin: boolean = false
  user: User = {
    id: 0,
    username: '',
    image: '',
    role: 0,
    email: '',
    active: -1,
    locked: -1
  }
  searchTerm: string = ''

  constructor(private categoryservice: CategoryService, private router: Router, private location: Location, private userservice: UserService){}

  ngOnInit(): void {
    this.getCategories();
    this.getUserInfo();
  }
  
  private getCategories(){
    this.categoryservice.getAllcategory().subscribe(data=>{
      this.categories = data
      this.categories.forEach(category=>this.categoryservice.getSubcategory(category.id).subscribe({
        next: subcategory=>{
          category.subcategories=subcategory
        },
        error: e=>{
          if (e.status==404){
            
          }
        }
      }))
    })
  }

  newsByCategory(categoryid: number){
    this.router.navigate(['/home'],{queryParams: {categoryid: categoryid}})
  }
  newsBySubcategory(subcategoryid: number){
    this.router.navigate(['/home'], {queryParams: {subcategoryid: subcategoryid}})
  }

  getUserInfo() {
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.userservice.getUserInfo().subscribe(data=> this.user =data)
      this.isLogin = true
    }
  }

  logout() {
    window.sessionStorage.removeItem('localhost:4200/Jwt-Token')
    // this.router.navigate(['/home'])
    window.location.reload()
  }

  searchSubmit(){
    console.log(this.searchTerm)
    this.router.navigate(['/home'],{queryParams: {keyword: this.searchTerm}})
  }

  getSavedNews(){
    this.router.navigate(['/home'], {queryParams: {saved: 'get'}})
  }
  
  info(){
    this.router.navigate(['/accountinfo'])
  }

}

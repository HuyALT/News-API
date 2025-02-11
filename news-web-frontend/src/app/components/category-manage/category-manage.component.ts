import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../service/categoryService';
import { Category } from '../../models/category';
import { ViewChild } from '@angular/core';
import { ElementRef } from '@angular/core';
import { AdminService } from '../../service/adminService';

@Component({
  selector: 'app-category-manage',
  templateUrl: './category-manage.component.html',
  styleUrl: './category-manage.component.css'
})
export class CategoryManageComponent implements OnInit {
  @ViewChild('overlay') overlay!: ElementRef;
  @ViewChild('formContainer') formContainer!: ElementRef;

  categorylist: Category[] = []
  categorySelect: Category = {
    id: 0,
    name: '',
    sort_name: '',
    subcategories: []
  }
  isEdit: boolean = false

  constructor(private categoryservice: CategoryService, private adminservice: AdminService){ }

  ngOnInit(): void {
    this.getAllcategory()
  }

  getAllcategory(){
    this.categoryservice.getAllcategory().subscribe({
      next: data=>this.categorylist = data
    })
  }

  showForm(){
    this.isEdit=false
    this.overlay.nativeElement.style.display = 'block'
    this.formContainer.nativeElement.style.display = 'block'
    this.categorySelect = {
      id: 0,
      name: '',
      sort_name: '',
      subcategories: []
    }
  }
  hideForm(){
    this.overlay.nativeElement.style.display = 'none'
    this.formContainer.nativeElement.style.display = 'none'
    this.isEdit = false
  }
  editCategory(categoryid: number){
    this.isEdit = true
    this.categoryservice.getCategoryById(categoryid).subscribe({
      next: data=>{
        this.categorySelect = data
        this.overlay.nativeElement.style.display = 'block'
        this.formContainer.nativeElement.style.display = 'block'
      }
    })
  }
  deleteCategory(categoryid: number){
    this.adminservice.deleteCategory(categoryid).subscribe({
      next: data=>{
        if (data==''){
          alert('không thể xóa')
        }
        this.getAllcategory()
      },
      error: e=>{
        if (e.status==204){
          alert('không thể xóa')
        }
      }
    })
  }

  onSunmitForm() {
    if (this.isEdit){
      this.adminservice.updateCategory(this.categorySelect.id, this.categorySelect).subscribe({
        next: data=>{
          this.getAllcategory()
          this.hideForm()
        }
      })
    }
    else {
      this.adminservice.addCategory(this.categorySelect).subscribe({
        next: data=>{
          this.getAllcategory()
          this.hideForm()
        },
        error: e=>{
          if (e.status==400){
            alert('Thiếu dữ liệu')
          }
        }
      })
    }
  }

}

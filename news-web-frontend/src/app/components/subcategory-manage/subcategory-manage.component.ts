import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { ElementRef } from '@angular/core';
import { subcategory } from '../../models/subcategory';
import { CategoryService } from '../../service/categoryService';
import { Category } from '../../models/category';
import { AdminService } from '../../service/adminService';

@Component({
  selector: 'app-subcategory-manage',
  templateUrl: './subcategory-manage.component.html',
  styleUrl: './subcategory-manage.component.css'
})
export class SubcategoryManageComponent implements OnInit {
  @ViewChild('overlay') overlay!: ElementRef;
  @ViewChild('formContainer') formContainer!: ElementRef;
  isEdit: boolean = false

  subcategorySelect: subcategory ={
    id: 0,
    category_id: 0,
    name: '',
    sort_name: ''
  }
  subcategorylist: subcategory[]=[]
  categorylist: Category[]=[]

  constructor(private categoryservice: CategoryService, private adminservce: AdminService){}

  ngOnInit(): void {
    this.getAllSubCategory()
    this.getAllCategory()
    
  }

  getAllSubCategory(){
    this.categoryservice.getAllSubcategory().subscribe({
      next: data=>this.subcategorylist = data
    })
  }
  getAllCategory(){
    this.categoryservice.getAllcategory().subscribe({
      next: data=>{
        this.categorylist = data
      }
    })
  }

  showForm(){
    this.isEdit==false
    this.overlay.nativeElement.style.display = 'block'
    this.formContainer.nativeElement.style.display = 'block'
    this.subcategorySelect = {
      id: 0,
      category_id: 0,
      name: '',
      sort_name: ''
    }
    this.getAllCategory()
  }
  hideForm(){
    this.overlay.nativeElement.style.display = 'none'
    this.formContainer.nativeElement.style.display = 'none'
    this.isEdit = false
  }

  editSubcategory(subcategoryid: number) {
    this.isEdit = true
    this.categoryservice.getSubcategoryById(subcategoryid).subscribe({
      next: data=>{
        this.subcategorySelect = data
        this.overlay.nativeElement.style.display = 'block'
        this.formContainer.nativeElement.style.display = 'block'
      }
    })
  }

  deleteSubcategory(subcategoryid: number){
    this.adminservce.deleteSubcategory(subcategoryid).subscribe({
      next: data=>{
        if (data==null){
          alert('Không thể xóa')
        }else{
          this.getAllSubCategory()
        }
      },
      error: e=>{
        if (e.status==204){
          alert('Không thể xóa')
        }
      }
    })
  }
  
  onSubmitForm(){
    if (this.isEdit) {
      this.adminservce.updateSubcategory(this.subcategorySelect.id, this.subcategorySelect).subscribe({
        next: data=>{
          this.getAllSubCategory()
          this.hideForm()
        }
      })
    }
    else {
      this.adminservce.addSubcategory(this.subcategorySelect).subscribe({
        next: data=>{
          this.getAllCategory()
          this.hideForm()
        }
      })
    }
  }

}

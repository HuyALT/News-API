import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { news } from '../../models/news';
import { AdminService } from '../../service/adminService';
import { Category } from '../../models/category';
import { subcategory } from '../../models/subcategory';
import { CategoryService } from '../../service/categoryService';
import { NewsService } from '../../service/newsService';

@Component({
  selector: 'app-news-manage',
  templateUrl: './news-manage.component.html',
  styleUrl: './news-manage.component.css'
})
export class NewsManageComponent implements OnInit {
  @ViewChild('overlay') overlay!: ElementRef;
  @ViewChild('formContainer') formContainer!: ElementRef;

  newslist: news[] = []
  categorylist: Category[] = []
  subcategorylist: subcategory[]=[]
  searchTerm: string = ''
  newsdetail: news = {
    id: 0,
    title: '',
    image: '',
    type: 0,
    link: '',
    summary: '',
    sort_title: '',
    content: '',
    user_id: 0,
    active: 0,
    category_id: 0,
    subcategory_id: 0,
    create_at: '',
    update_at: '',
    category_name: '', 
    subcategory_name: ''
  }

  selectCategory: number = 1;
  selectSubCategory: number = 1;
  selectType: number = 1;
  isEdit: boolean = false

  constructor(private adminservice: AdminService, private categoryservice: CategoryService, private newsservice: NewsService){ }

  ngOnInit(): void {
    this.getAllNews()
    this.categoryservice.getAllcategory().subscribe({
      next: data=>{
        this.categorylist = data
        this.selectCategory = data[0].id
        this.categoryservice.getSubcategory(this.selectCategory).subscribe({
          next: subcategory=>{
            this.subcategorylist = subcategory
            this.selectSubCategory = subcategory[0].id
          }
        })
      }
    })
  }

  getAllNews(){
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.adminservice.getAllNews().subscribe({
        next: data=>this.newslist = data
      })
  }
  }


  get fillterData(): news[]{
    if (this.searchTerm==''){
      return this.newslist
    }
    return this.newslist.filter(news=>
      news.id.toString().includes(this.searchTerm) ||
      news.title.toLowerCase().includes(this.searchTerm.toLowerCase())||
      news.sort_title.toLowerCase().includes(this.searchTerm.toLowerCase())||
      news.create_at.includes(this.searchTerm) || 
      news.update_at.includes(this.searchTerm)
    )
  }

  showForm(){
    this.overlay.nativeElement.style.display = 'block'
    this.formContainer.nativeElement.style.display = 'block'
    this.newsdetail = {
      id: 0,
      title: '',
      image: '',
      type: 0,
      link: '',
      summary: '',
      sort_title: '',
      content: '',
      user_id: 0,
      active: 0,
      category_id: 0,
      subcategory_id: 0,
      create_at: '',
      update_at: '',
      category_name: '', 
      subcategory_name: ''
    }
  }
  hideForm(){
    this.overlay.nativeElement.style.display = 'none'
    this.formContainer.nativeElement.style.display = 'none'
    this.isEdit = false
  }

  onChangeCategory(event: Event){
    const selectedElement = event.target as HTMLSelectElement
    this.categoryservice.getSubcategory(parseInt(selectedElement.value)).subscribe(data=>{
      this.subcategorylist = data
      this.selectSubCategory = data[0].id
    })
    this.selectCategory = parseInt(selectedElement.value)
  }

  editnews(newsid: number){
    this.isEdit = true
    this.newsservice.getDetail(newsid).subscribe({
      next: data=>{
        this.newsdetail=data
        this.overlay.nativeElement.style.display = 'block'
        this.formContainer.nativeElement.style.display = 'block'
        this.selectCategory = data.category_id
        this.selectSubCategory = data.subcategory_id
        this.selectType = data.type

        this.categoryservice.getSubcategory(this.selectCategory).subscribe({
          next: subcategory=>{
            this.subcategorylist = subcategory
            
          }
        })
      }
    })
  }

  onSubmitForm() {
    if (this.isEdit){
      this.adminservice.updateNews(this.newsdetail, this.newsdetail.id).subscribe({
        next: data=>{
          this.getAllNews()
          this.hideForm()
        },
        error: e=>{
          if (e.status==400) {
            alert('không thể sửa')
          }
        }
      })
    }
    else{
      this.newsdetail.category_id = this.selectCategory
      this.newsdetail.subcategory_id = parseInt(this.selectSubCategory.toString())
      this.newsdetail.type = parseInt(this.selectType.toString())
      this.newsdetail.active = 1
      this.adminservice.addNews(this.newsdetail).subscribe({
        next: data=>{
          this.getAllNews()
          this.hideForm()
        },
        error: e=> {
          if (e.status==400){
            alert('Thiếu dữ liệu')
          }
        }
      })
    }
  }

  changeHideAndShow(newsid: number){
    this.adminservice.hideandshow(newsid).subscribe({
      next: data=>{
        this.getAllNews()
      },
      error: e=>{
        if (e.status==204) {
          alert('Thao tác thất bại')
        }
      }
    })
  }
}

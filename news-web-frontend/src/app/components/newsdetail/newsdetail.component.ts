import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { news } from '../../models/news';
import { NewsService } from '../../service/newsService';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../service/categoryService';
import { CommentService } from '../../service/commentService';
import { UserService } from '../../service/userService';
import { NewsComment } from '../../models/comment';
import { forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { SaveService } from '../../service/saveService';
import { SaveNews } from '../../models/save';
import { SummaryService } from '../../service/summaryService';


@Component({
  selector: 'app-newsdetail',
  templateUrl: './newsdetail.component.html',
  styleUrl: './newsdetail.component.css',
  encapsulation: ViewEncapsulation.None
})
export class NewsdetailComponent implements OnInit {
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

  commentContent: string =''
  summaryContent: string = 'Đang tóm tắt...'

  commentList: NewsComment[] = []

  id: number = 0
  isLogin: boolean = false
  safeURL: SafeResourceUrl = '';
  isSaved: boolean = false
  isSummary: boolean = false

  constructor(private newsservice: NewsService, private route: ActivatedRoute, private router: Router,
    private categoryservice: CategoryService,
    private commentservice: CommentService,
    private userservice: UserService,
    private savedservice: SaveService,
    private summaryService: SummaryService){ }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.getDetailNews()
    this.getAllComment()
    this.checkLogin()
    this.checkSave()
  }

  getDetailNews(){
    this.newsservice.getDetail(this.id).subscribe(data=> {
      this.newsdetail = data
      this.categoryservice.getCategoryById(this.newsdetail.category_id).subscribe(category=> this.newsdetail.category_name= category.name)
      this.categoryservice.getSubcategoryById(this.newsdetail.subcategory_id).subscribe(subcategory=>this.newsdetail.subcategory_name=subcategory.name)
    });
  }

  getAllComment() {
    this.commentservice.getCommentByNewid(this.id).pipe(
      switchMap(comments => {
        const userObservables = comments.map(comment => 
          this.userservice.getUserById(comment.userId).pipe(
            map(user => {
              comment.userInfo = user;
              return comment;
            })
          )
        );
        return forkJoin(userObservables);
      })
    ).subscribe({
      next: commentsWithUserInfo => {
        this.commentList = commentsWithUserInfo;
        
      },
      error: e=> {
        if (e.status==404){

        }
      }
    })
  }

  checkLogin() {
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
      this.isLogin = true
    }
  }
  login() {
    this.router.navigate(['/login'])
  }

  addComment(){
    this.commentservice.addComment(this.id, this.commentContent).subscribe(data=>{
      this.getAllComment()
      this.commentContent = ''
    })
    
  }

  checkSave(){
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null)
    {
      this.savedservice.getAllSave().subscribe({
        next: data=>{
          if (data.find(o=>o.newsid==this.id)) {
            this.isSaved = true
          }
          else {
            this.isSaved =false
          }
        },
        error: e=>{
          if (e.status==400){
            this.isSaved =false
          }
        }
      })
    }
  }

  addSaved(){
    this.savedservice.addSaved(this.id).subscribe({
      next: data=>{
        this.isSaved = true
      }
    })
  }

  deleteSaved(){
    if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null)
    {
      this.savedservice.getAllSave().subscribe({
        next: data=>{
          const savednews = data.find(o=>o.newsid==this.id)
          console.log(savednews)
          if (savednews){
            this.savedservice.deleteSaved(savednews.id).subscribe({
              next: data=>{
                
              }
            })
            this.isSaved = false
          }
        }
      })
    }

  }

  summary(){
    this.isSummary = true
    this.summaryService.getSummary(this.newsdetail.content).subscribe({
      next: data=>{
        this.summaryContent = data.summaryData
      }
    })
  }
};

import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../service/newsService';
import { news } from '../../models/news';
import { ActivatedRoute, Router } from '@angular/router';
import { SaveService } from '../../service/saveService';
import { forkJoin } from 'rxjs';
import { map, mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  newslist: news[] =[] ;
  itemsPerPage = 5;
  totalPages: number = 0;
  currentPage = 1;
  currentPageNews: news[] = [];

  constructor(private newsservice: NewsService, private saveservice: SaveService, private route: ActivatedRoute, private router: Router){ 

  }

  ngOnInit(): void {
    
    this.route.queryParamMap.subscribe(params=> {
      const categoryid = params.get('categoryid')
      const subcategoryid = params.get('subcategoryid')
      const keyword = params.get('keyword')
      const saved = params .get('saved')
      if (categoryid!=null){
        this.newsservice.getByCategory(categoryid).subscribe(data=>{
          this.newslist = data
          this.afterDataLoad()
        });
      }
      else
      if (subcategoryid!=null){
        this.newsservice.getBySubcategory(subcategoryid).subscribe(data=>{
          this.newslist = data
          this.afterDataLoad()
        });
      }
      else if (keyword!=null&&keyword!=''){
        this.newsservice.searchByKeyword(keyword).subscribe({
          next: data=>{
            this.newslist = data
            this.afterDataLoad()
          }
        })
      } else if (saved!=null){
        if (typeof window !== 'undefined' && window.sessionStorage.getItem('localhost:4200/Jwt-Token')!=null){
          this.saveservice.getAllSave().pipe(
            mergeMap(data => {
              const newsDetailObservables = data.map(save => this.newsservice.getDetail(save.newsid));
              return forkJoin(newsDetailObservables);
            })
          ).subscribe({
            next: newsList => {
              this.newslist = newsList;
            
              this.afterDataLoad();
            },
            error: err => {
              console.error('Error fetching news details', err);
            }
          });
        } else {
          this.afterDataLoad();
        }
        
      }
      else{
        this.getAllnews()
      }
    });
  }

  afterDataLoad() {
    this.totalPages = Math.ceil(this.newslist.length / this.itemsPerPage);
    this.setPage(1);
  }

  getAllnews(){
    this.newsservice.getAllNews().subscribe(data=> {
      this.newslist = data
      this.afterDataLoad()
    });
  }

  setPage(page: number) {
    const startIndex = (page - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.currentPageNews = this.newslist.slice(startIndex, endIndex);
    this.currentPage = page;
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.setPage(this.currentPage + 1);
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.setPage(this.currentPage - 1);
    }
  }

  newsdetail(id: number) {
    this.router.navigate(['/news', id])
  }

}

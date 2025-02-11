import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { NewsdetailComponent } from './components/newsdetail/newsdetail.component';
import { SignupComponent } from './components/signup/signup.component';
import { VerifyOTPComponent } from './components/verify-otp/verify-otp.component';
import { AdminOptionComponent } from './components/admin-option/admin-option.component';
import { Page403Component } from './components/page403/page403.component';
import { UserManageComponent } from './components/user-manage/user-manage.component';
import { NewsManageComponent } from './components/news-manage/news-manage.component';
import { SafePipe } from './safePipe';
import { CategoryManageComponent } from './components/category-manage/category-manage.component';
import { SubcategoryManageComponent } from './components/subcategory-manage/subcategory-manage.component';
import { AccountInfoComponent } from './components/account-info/account-info.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

@NgModule({
  declarations: [
    SafePipe,
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    NewsdetailComponent,
    SignupComponent,
    VerifyOTPComponent,
    AdminOptionComponent,
    Page403Component,
    UserManageComponent,
    NewsManageComponent,
    CategoryManageComponent,
    SubcategoryManageComponent,
    AccountInfoComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch()),
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

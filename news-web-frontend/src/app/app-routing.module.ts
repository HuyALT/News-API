import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NewsdetailComponent } from './components/newsdetail/newsdetail.component';
import { SignupComponent } from './components/signup/signup.component';
import { VerifyOTPComponent } from './components/verify-otp/verify-otp.component';
import { Page403Component } from './components/page403/page403.component';
import { AdminOptionComponent } from './components/admin-option/admin-option.component';
import { UserManageComponent } from './components/user-manage/user-manage.component';
import { NewsManageComponent } from './components/news-manage/news-manage.component';
import { CategoryManageComponent } from './components/category-manage/category-manage.component';
import { SubcategoryManageComponent } from './components/subcategory-manage/subcategory-manage.component';
import { AccountInfoComponent } from './components/account-info/account-info.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

const routes: Routes = [
  {path:'home', component: HomeComponent, pathMatch: 'full'},
  {path:'', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path:'news/:id', component: NewsdetailComponent, pathMatch:'full'},
  {path: 'signup', component: SignupComponent, pathMatch:'full'},
  {path: 'verify-OTP', component: VerifyOTPComponent},
  {path: 'forbidden', component: Page403Component, pathMatch:'full'},
  {path: 'usermanager', component: UserManageComponent, pathMatch: 'full'},
  {path: 'newsmanager', component: NewsManageComponent, pathMatch: 'full'},
  {path: 'categorymanager', component: CategoryManageComponent, pathMatch: 'full'},
  {path: 'subcategorymanager', component: SubcategoryManageComponent, pathMatch: 'full'},
  {path: 'accountinfo', component: AccountInfoComponent, pathMatch: 'full'},
  {path: 'changePassword', component: ChangePasswordComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

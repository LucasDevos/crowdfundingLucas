import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { PmComponent } from './pm/pm.component';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { NavbarComponent } from './navbar/navbar.component';
import { ProjectsComponent } from './projects/projects.component';
import { DonationsComponent } from './donations/donations.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectUpdateComponent } from './project-update/project-update.component';
import { NewProjectComponent } from './new-project/new-project.component';
import { ContributorsComponent } from './contributors/contributors.component';
import { ContributorDonationsDetailsComponent } from './contributor-donations-details/contributor-donations-details.component';
import { CategoriesComponent } from './categories/categories.component';
import { ContributorDetailsComponent } from './contributor-details/contributor-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent, 
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    PmComponent,
    NavbarComponent,
    ProjectsComponent,
    DonationsComponent,
    ProjectDetailsComponent,
    ProjectUpdateComponent,
    NewProjectComponent,
    ContributorsComponent,
    ContributorDonationsDetailsComponent,
    CategoriesComponent,
    ContributorDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }

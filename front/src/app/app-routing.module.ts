import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { PmComponent } from './pm/pm.component';
import { AdminComponent } from './admin/admin.component';
import { ProjectsComponent } from './projects/projects.component';
import { DonationsComponent } from './donations/donations.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectUpdateComponent } from './project-update/project-update.component';
import { NewProjectComponent } from './new-project/new-project.component';
import { ContributorsComponent } from './contributors/contributors.component';
import { CategoriesComponent } from './categories/categories.component';
import { ContributorDetailsComponent } from './contributor-details/contributor-details.component';

const routes: Routes = [
    {path: 'home', component: HomeComponent},
    {path: 'user', component: UserComponent},
    {path: 'pm', component: PmComponent},
    {path: 'admin', component: AdminComponent},
    {path: 'auth/login', component: LoginComponent},
    {path: 'signup', component: RegisterComponent},
    {path: '', redirectTo: 'home', pathMatch: 'full'},

    //ajouts
    {path: 'projects', component: ProjectsComponent},
    {path: 'project/:id', component: ProjectDetailsComponent},
    {path: 'project/add-update/:id', component: ProjectUpdateComponent},
    {path: 'new-project', component: NewProjectComponent},
    {path: 'update-project/:id', component: NewProjectComponent},
    {path: 'contributors', component: ContributorsComponent},
    {path: 'profile/:name', component: ContributorDetailsComponent},
    {path: 'donations', component: DonationsComponent},
    {path: 'categories', component: CategoriesComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

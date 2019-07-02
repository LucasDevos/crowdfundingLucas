import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Project } from '../models/Project';
import { ProjectService } from '../crow-services/project.service';
import { Category } from '../models/Category';
import { CategoryService } from '../crow-services/category.service';
import { Contributor } from '../models/Contributor';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  //@ViewChild('searchBar', {static: false}) searchBar: ElementRef;
  @ViewChild('searchBar') searchBar: ElementRef;

  projects:Project[];
  categories:Category[];

  activeField:string="id";
  toggleState:boolean=false;
  categoryNumber:number=9999; //9999 = toutes les categories, un autre chiffre = id de la categorie

  user:Contributor;

  constructor(private ps:ProjectService, private cats:CategoryService, private data:DataService) { }

  ngOnInit() {
    this.user = this.data.getCurrentUser();
    this.getProjects();
  }

  getProjects(){
    this.ps.findAll().subscribe(
      res => {
        this.projects = res;
        this.getCategories();
      }
    );
  }

  getCategories(){
    this.cats.findAll().subscribe(
      res => {
        this.categories = res;
      }
    );
  }

  // Lance la recherche, réactualise la liste des projets affichés
  search(){
    console.log("on lanche la recherche avec les termes "+this.searchBar.nativeElement.value);
    this.ps.findAllWithSearch(this.searchBar.nativeElement.value, this.activeField, this.toggleState, this.categoryNumber).subscribe(
      res => {
        this.projects = res;
      }
    );
  }

  // Appelé lors du clic sur un field. Change le field sélectionné / inverse l'ordre des entrées, réactualise la liste
  toggleField(field:string){
    if (this.activeField != field){
      this.activeField = field;
      this.toggleState = false;
    } else {
      this.toggleState = !this.toggleState;
    }
    this.search();
  }

  deleteProject(id:number){
    this.ps.deleteById(id).subscribe(
      res => {
        console.log("projet "+id+" supprime");
        this.search();
      }
    );
  }

}

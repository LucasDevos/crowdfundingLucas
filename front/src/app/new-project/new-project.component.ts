import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../crow-services/category.service';
import { Category } from '../models/Category';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Project } from '../models/Project';
import { ProjectService } from '../crow-services/project.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Contributor } from '../models/Contributor';
import { ContributorService } from '../crow-services/contributor.service';
import { p } from '@angular/core/src/render3';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-new-project',
  templateUrl: './new-project.component.html',
  styleUrls: ['./new-project.component.css']
})
export class NewProjectComponent implements OnInit {

  user:Contributor;

  pictureUrl:String="...";
  categories:Category[];
  contributors:Contributor[];

  projectForm:FormGroup;
  project:Project;// = new Project();
  projectId:number;
  //selected:Contributor;

  loaded:boolean = false;

  constructor(private cats:CategoryService, private ps:ProjectService, private cs:ContributorService,
    private fb:FormBuilder, private route: Router, private router:ActivatedRoute,
    private data:DataService) { }

  ngOnInit() {
    this.user = this.data.getCurrentUser();
    //this.getId();
    this.getCategories();
    //this.initProjectForm(); //mis à la suite de getCategories, mais il faudra trouver une méthode plus élégante
  }

  // LOADING DATA - Var "loading" is true when finished ---------------------------------------

  // 1
  getCategories(){
    this.cats.findAll().subscribe(
      res => {
        this.categories = res;
        this.getContributors();
      }
    );
  }

  // 2
  getContributors(){
    this.cs.findAll().subscribe(
      res => {
        this.contributors = res;
        this.getId();
      }
    );
  }

  // 3
  getId(){
    this.router.paramMap.subscribe(
      res=>{
        console.log("param map", +res.get("id"));
        this.projectId = +res.get("id");
        if (this.projectId != 0)
          this.getProject();
        else
          this.initProjectForm();
      }
    );
  }

  // 4a
  getProject(){
    this.ps.findById(this.projectId).subscribe(
      res => {

        this.project = res;

        let cat:Category;
        for (let c of this.categories){
          if (c.id == this.project.category.id){
            console.log("la categorie est "+c.name);
            cat = c;
          }
        }

        let crea:Contributor;
        for (let cr of this.contributors){
          if (cr.id == this.project.creator.id){
            console.log("le createur est "+cr.nickname);
            crea = cr;
          }
        }
        
        this.projectForm = this.fb.group({
          id: this.project.id,
          title: this.project.title,
          pitch: this.project.pitch,
          description: this.project.description,
          thumbnailPath: this.project.thumbnailPath,
          donationGoal: this.project.donationGoal,
          endDate : this.project.endDate,
          category: cat,
          creator: crea
        });
        this.pictureUrl = this.project.thumbnailPath;
        this.loaded = true;
      }
    );
  }

  // 4b
  initProjectForm(){
    let noPicture:string = "https://gaia-speaks.org/files/2018/02/your_picture_here.jpg";
    this.projectForm = this.fb.group({
      title: "",
      pitch: "",
      description: "",
      thumbnailPath: noPicture,
      donationGoal: 100,
      endDate : Date.now(),
      category: this.categories[0]
    });
    this.pictureUrl = noPicture;
    this.project = new Project();
    this.loaded = true;
  }

  // END OF LOADING ----------------------------------------------------------------

  updatePicture(event:any){
    //console.log(event.target.value);
    this.pictureUrl = event.target.value;
  }

  saveProject(){
    console.log("On sauvegarde ce projet : ",this.projectForm.value);
    let p:Project = this.projectForm.value;
    if (this.projectId == 0){ //si c'est un nouveau projet, on ajoute également le champ creator
      p.creator = this.data.getCurrentUser();
    }
    //console.log("selected is "+this.selected.nickname);
    this.ps.save(p).subscribe(
      res =>
      {
        console.log("Nouveau projet : ", res);
        this.route.navigate(["/home"]);
      }
    );
  }

}

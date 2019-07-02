import { Component, OnInit } from '@angular/core';
import { Project } from '../models/Project';
import { ProjectService } from '../crow-services/project.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ProjectUpdate } from '../models/ProjectUpdate';
import { ProjectUpdateService } from '../crow-services/project-update.service';

@Component({
  selector: 'app-project-update',
  templateUrl: './project-update.component.html',
  styleUrls: ['./project-update.component.css']
})
export class ProjectUpdateComponent implements OnInit {

  projectId:number;
  project:Project;

  updateForm:FormGroup;

  update:ProjectUpdate;

  constructor(private ps:ProjectService, private pus:ProjectUpdateService,
    private router:ActivatedRoute, private route: Router, private fb:FormBuilder) { }

  ngOnInit() {
    this.getId();
  }

  getId(){
    this.router.paramMap.subscribe(
      res=>{
        this.projectId = +res.get("id");
        this.getProject();
      }
    );
  }
  getProject(){
    this.ps.findById(this.projectId).subscribe(
      res => {
        this.project = res;
        this.initUpdateForm();
      }
    );
  }
  initUpdateForm(){
    this.updateForm = this.fb.group({
      title:"",
      description:"",
    });
    this.update = new ProjectUpdate();
  }

  saveUpdate(){
    console.log("On sauvegarde cette nouvelle : "+this.updateForm.value);
    this.pus.save(this.updateForm.value, this.projectId).subscribe(
      res =>
      {
        this.route.navigate(["/project/"+this.projectId]);
      }
    );
  }


}

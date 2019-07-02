import { Component, OnInit } from '@angular/core';
import { Contributor } from '../models/Contributor';
import { DataService } from '../crow-services/data.service';
import { Project } from '../models/Project';
import { ProjectService } from '../crow-services/project.service';
import { ActivatedRoute } from '@angular/router';
import { ContributorService } from '../crow-services/contributor.service';

@Component({
  selector: 'app-contributor-details',
  templateUrl: './contributor-details.component.html',
  styleUrls: ['./contributor-details.component.css']
})
export class ContributorDetailsComponent implements OnInit {

  username:string;
  user:Contributor;
  projects:Project[];
  backedProjects:Project[];

  constructor(private data:DataService, private ps:ProjectService, private cs:ContributorService,
    private router:ActivatedRoute) { }

  ngOnInit() {
    //this.user = this.data.getCurrentUser();
    this.getUsername();
    //this.getProjects();
    //this.getBackedProjects();
  }

  getUsername(){
    this.router.paramMap.subscribe(
      res=>{
        this.username = res.get("name");
        this.getUser();
      }
    );
  }

  getUser(){
    this.cs.findByUsername(this.username).subscribe(
      res => {
        this.user = res;
        this.getProjects();
        this.getBackedProjects();
      }
    );
  }

  getProjects(){
    this.ps.findAllFromContributor(this.user.id).subscribe(
      res => {
        this.projects = res;
      }
    );
  }

  getBackedProjects(){
    this.ps.findAllBackedByContributor(this.user.id).subscribe(
      res => {
        this.backedProjects = res;
      }
    );
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../models/Project';
import { ProjectService } from '../crow-services/project.service';
import { Contributor } from '../models/Contributor';
import { ContributorService } from '../crow-services/contributor.service';
import { DonationService } from '../crow-services/donation.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  projectId:number;
  project:Project;
  contributors:Contributor[];
  donationTypes:string[];
  giveMoney = false;
  amountGiven = 0.0;
  user:Contributor;

  donationForm:FormGroup;

  constructor(private router:ActivatedRoute, private projectService:ProjectService,
    private contributorService:ContributorService, private donationService:DonationService,
    private fb:FormBuilder, private data:DataService) { }

  ngOnInit() {
    this.getId();
  }

  //1
  getId(){
    this.router.paramMap.subscribe(
      res=>{
        this.projectId = +res.get("id");
        this.getProject();
        //console.log("param map", res);
      }
    );
  }

  //2
  getProject(){
    this.amountGiven = 0.0;
    this.projectService.findById(this.projectId).subscribe(
      res => {
        this.project = res;
        console.log("les updates du projet : "+res.updates.length);
        this.getContributors();
      }
    );
  }

  //3
  getContributors(){
    this.contributorService.findAllOrderedByDonationsForProject(this.projectId).subscribe(
      res => {
        this.contributors = res;
        this.getDonationTypes();
      }
    );
  }

  //4
  getDonationTypes(){
    this.donationService.getDonationTypes().subscribe(
      res => {
        this.donationTypes = res;
        console.log("donation types : "+this.donationTypes.length);
        this.initDonationForm();
      }
    );
  }

  //5
  initDonationForm(){
    this.user = this.data.getCurrentUser();
    //console.log("user is "+this.user.nickname);
    this.donationForm = this.fb.group({
      amount : 5.0,
      project : this.project,
      contributor : this.user,
      donationType: this.donationTypes[0]
    });
  }

  // ----------------------------------------------------

  getWidth(){
    return ""+(this.project.donationCurrent / this.project.donationGoal * 100.0)+"%";
  }
  getPercentage(){
    return (this.project.donationCurrent / this.project.donationGoal * 100.0);
  }

  makeDonation(){
    console.log("we'll make donation: "+this.donationForm.value);
    this.donationService.save(this.donationForm.value).subscribe(
      res => {
        this.amountGiven = res.amount;
        this.giveMoney = false;
        console.log("amountGiven is "+this.amountGiven);
        //this.reoladProject();
        this.getProject();
      }
    );
  }

  //same as getProject but don't relaod the rest
  reoladProject(){
    this.amountGiven = 0.0;
    this.projectService.findById(this.projectId).subscribe(
      res => {
        this.project = res;
      }
    );
  }

}

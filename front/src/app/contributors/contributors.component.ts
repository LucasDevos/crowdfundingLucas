import { Component, OnInit } from '@angular/core';
import { Contributor } from '../models/Contributor';
import { ContributorService } from '../crow-services/contributor.service';
import { CategoryService } from '../crow-services/category.service';
import { Category } from '../models/Category';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-contributors',
  templateUrl: './contributors.component.html',
  styleUrls: ['./contributors.component.css']
})
export class ContributorsComponent implements OnInit {

  contributors:Contributor[];
  contributorsAlphabetically:Contributor[];
  maxAmount:number;
  categories:Category[];

  constructor(private cs:ContributorService, private cats:CategoryService, private data:DataService) { }

  ngOnInit() {
    this.getContributors();
    this.getContributorsAlphabetically();
    this.getMaxAmount();
    this.getCategories();
  }

  getCategories(){
    this.cats.findAll().subscribe(
      res => {
        this.categories = res;
      }
    );
  }

  getContributors(){
    this.cs.findAllOrderedByDonations().subscribe(
      res => {
        this.contributors = res;
      }
    );
  }

  getContributorsForCategory(category_id:number){
    this.cs.findAllOrderedByDonationsForCategory(category_id).subscribe(
      res => {
        this.contributors = res;
      }
    );
  }

  getContributorsAlphabetically(){
    this.cs.findAllOrderedByNickname().subscribe(
      res => {
        this.contributorsAlphabetically = res;
      }
    );
  }

  getMaxAmount(){
    this.cs.maxAmount().subscribe(
      res => {
        console.log("maxAmount récupéré : "+res);
        this.maxAmount = res;
      }
    );
  }

  test(){
    return 80;
  }

  test2(){
    console.log("coucou");
  }

  getAmountDonated(c:Contributor){
    //console.log(c.id);
    return 80;
    /*this.cs.amountDonated(c.id).subscribe(
      res => {
        console.log("test "+res);
        //this.amount = res;
        return(res);
        //return(this.amount);
      }
    )*/
  }

}

import { Component, OnInit, Input } from '@angular/core';
import { Contributor } from '../models/Contributor';
import { ContributorService } from '../crow-services/contributor.service';

@Component({
  selector: 'app-contributor-donations-details',
  templateUrl: './contributor-donations-details.component.html',
  styleUrls: ['./contributor-donations-details.component.css']
})
export class ContributorDonationsDetailsComponent implements OnInit {

  
  @Input() contributor:Contributor; // contributeur correspondant à cet objet
  @Input() maxAmount:number;        // total de donation maximum, tous contributeurs confondus

  total:number;                     // total des donations par le contributeur (contributor)
  totalPerCategories:number[];      // total des donations par catégories par le contributeur (ex [500, 200, 0, 800, 0])

  constructor(private cs:ContributorService) { }

  ngOnInit() {
    this.getDonationTotal();
    this.getDonationPerCategories();
  }

  getDonationTotal(){
    this.cs.amountDonated(this.contributor.id).subscribe(
      res => {
        console.log("res is "+res);
        this.total = res;
      }
    );
  }

  getDonationPerCategories(){
    this.cs.amountsDonatedByCategory(this.contributor.id).subscribe(
      res => {
        console.log("les montants sont "+res);
        this.totalPerCategories = res;
      }
    );
  }

  // TODO retourne la couleur de la progressBar. Pour l'instant c'est écrit en brut, il faudrait récupérer le champ couleur
  // sur l'objet Category ! (chaque catégorie a une couleur)
  getColor(i:number){
    switch (i){
      case 0 : return "#28a745";
      case 1 : return "#dc3545";
      case 2 : return "#ffc107";
      case 3 : return "#007bff";
      case 4 : return "#17a2b8";
      default: return "#888888";
    }
  }

  // retourne la longueur de la progressbar relative à maxAmount (càd la donation la plus haute, tous contributeurs confondus)
  // val est le montant de la donation. ex : val = 100, maxAmount = 400 => retourne "25%"
  getWidth(val){
    return ""+(val / this.maxAmount * 100.0)+"%";
  }

  // retourne la longueur de la progressbar relative au total des donations du contributeur actuel (contributor)
  // val est le montant de la donation. ex : val = 100, total = 500 => retourne "20%"
  getWidthCurrent(val){
    return ""+(val / this.total * 100.0)+"%";
  }

}

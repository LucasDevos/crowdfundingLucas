import { Component, OnInit } from '@angular/core';
import { DonationService } from '../crow-services/donation.service';
import { Donation } from '../models/Donation';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-donations',
  templateUrl: './donations.component.html',
  styleUrls: ['./donations.component.css']
})
export class DonationsComponent implements OnInit {

  donations:Donation[];

  activeField = "id"; //TODO : remplacer par une énumération. Valeurs possibles : "id", "dateTime", "amount", "contributor", "project"
  toggleState = false;

  constructor(private donationService:DonationService, private data:DataService) { }

  ngOnInit() {
    this.getDonations();
  }

  // Récupère la liste des donations. L'ordre d'affichage dépend du champ sélectionné et de son état
  getDonations(){
    this.donationService.findAllByField(this.activeField, this.toggleState).subscribe(
      res => {
        this.donations = res;
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
    this.getDonations();
  }
  

}

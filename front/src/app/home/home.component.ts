import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../auth/token-storage.service';
import { Project } from '../models/Project';
import { ProjectService } from '../crow-services/project.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;

  projects:Project[]; //added

  constructor(private token: TokenStorageService, private ps:ProjectService) { } //added ps

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    this.initProjects(); //added
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }


  // ADDED METHODS
  initProjects() {
    this.ps.findAllHome().subscribe(
      res => {
        console.log("blabla");
        this.projects = res;
      }
    );
  }
}

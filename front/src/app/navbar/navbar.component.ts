import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { getDefaultService } from 'selenium-webdriver/opera';
import { Contributor } from '../models/Contributor';
import { ContributorService } from '../crow-services/contributor.service';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private roles: string[];
  private authority: string;
  //info: any;
  user:Contributor;

  constructor(private tokenStorage: TokenStorageService, private cs:ContributorService, private data:DataService) { }

  ngOnInit() {
    this.mainInit();
    this.getUser();
  }

  mainInit(){
    if (this.tokenStorage.getToken()) {

      /*this.info = {
        token: this.tokenStorage.getToken(),
        username: this.tokenStorage.getUsername(),
        authorities: this.tokenStorage.getAuthorities()
      };*/

      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          this.data.setAuthority('admin');
          return false;
        } else if (role === 'ROLE_PM') {
          this.authority = 'pm';
          this.data.setAuthority('pm');
          return false;
        }
        this.authority = 'user';
        this.data.setAuthority('user');
        return true;
      });
    }
  }

  getUser(){
    let username:string = this.tokenStorage.getUsername();
    console.log("username is "+username);
    this.cs.findByUsername(username).subscribe(
      res => {
        this.user = res;
        this.data.setCurrentUser(this.user);
      }
    );
  }

  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }

}

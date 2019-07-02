import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TokenStorageService } from '../auth/token-storage.service';
import { Contributor } from '../models/Contributor';
import { ContributorService } from './contributor.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {


  private messageSource = new BehaviorSubject('default message');
  currentMessage = this.messageSource.asObservable();

  currentUser:Contributor;

  authority:string;

  constructor(private token: TokenStorageService, private cs:ContributorService) { }

  

  changeMessage(message: string) {
    this.messageSource.next(message)
  }

  getCurrentUserName(){
    return this.token.getUsername();
  }

  setCurrentUser(c:Contributor){
    this.currentUser = c;
  }

  getCurrentUser(){
    return this.currentUser;
  }

  setAuthority(auth:string){
    this.authority = auth;
  }
  
}

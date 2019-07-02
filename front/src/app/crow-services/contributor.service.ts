import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contributor } from '../models/Contributor';

@Injectable({
  providedIn: 'root'
})
export class ContributorService {

  private url="http://localhost:8080/crow/contributors";

  constructor(private http:HttpClient) { }

  findByUsername(name:string):Observable<Contributor>{
    return this.http.get<Contributor>(this.url+"/user="+name);
  }

  findAll():Observable<Contributor[]>{  //syntaxe à retenir, on renvoie un tableau d'Authors de type Observable (c'est une histoire de truc synchrone / asynchrone)
    return this.http.get<Contributor[]>(this.url);
  }

  findAllOrderedByDonations():Observable<Contributor[]>{  //syntaxe à retenir, on renvoie un tableau d'Authors de type Observable (c'est une histoire de truc synchrone / asynchrone)
    return this.http.get<Contributor[]>(this.url+"/orderedByDonations");
  }

  findAllOrderedByDonationsForCategory(category_id:number):Observable<Contributor[]>{ 
    return this.http.get<Contributor[]>(this.url+"/orderedByDonations/cat="+category_id);
  }

  findAllOrderedByDonationsForProject(project_id:number):Observable<Contributor[]>{ 
    return this.http.get<Contributor[]>(this.url+"/orderedByDonations/proj="+project_id);
  }

  findAllOrderedByNickname():Observable<Contributor[]>{
    return this.http.get<Contributor[]>(this.url+"/orderedByNickname");
  }

  amountDonated(id):Observable<number>{
    return this.http.get<number>(this.url+"/id="+id);
  }

  amountsDonatedByCategory(id):Observable<number[]>{
    return this.http.get<number[]>(this.url+"/id="+id+"/allCategories");
  }

  maxAmount():Observable<number>{
    return this.http.get<number>(this.url+"/maxAmount");
  }
}

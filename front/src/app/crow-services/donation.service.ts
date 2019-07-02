import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Donation } from '../models/Donation';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DonationService {

  private url="http://localhost:8080/crow/donations";

  constructor(private http:HttpClient) { }

  findAll():Observable<Donation[]>{
    return this.http.get<Donation[]>(this.url);
  }

  findAllByField(field:string, isDescending:boolean):Observable<Donation[]>{
    if (isDescending)
      return this.http.get<Donation[]>(this.url+"/"+field+"_desc");
    else
      return this.http.get<Donation[]>(this.url+"/"+field+"_asc");
  }

  getDonationTypes():Observable<string[]>{
    return this.http.get<string[]>(this.url+"/types");
  }

  save(d:Donation):Observable<Donation>{
    return this.http.post<Donation>(this.url+"/save", d);
  }
}

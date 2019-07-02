import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../models/Category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url="http://localhost:8080/crow/categories";

  constructor(private http:HttpClient) { }

  findAll():Observable<Category[]>{  //syntaxe à retenir, on renvoie un tableau d'Authors de type Observable (c'est une histoire de truc synchrone / asynchrone)
    return this.http.get<Category[]>(this.url);
  }

  save(c:Category):Observable<Category>{
    return this.http.post<Category>(this.url+"/save", c);
  }
}

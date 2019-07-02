import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../models/Project';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private url="http://localhost:8080/crow/projects";

  constructor(private http:HttpClient) { }

  findAll():Observable<Project[]>{
    return this.http.get<Project[]>(this.url+"/all");
  }

  findAllHome():Observable<Project[]>{
    return this.http.get<Project[]>(this.url);
  }

  findById(id:number):Observable<Project>{
    return this.http.get<Project>(this.url+"/"+id);
  }

  save(proj:Project):Observable<Project>{
    return this.http.post<Project>(this.url+"/saveProject", proj);
  }

  deleteById(id:number){
    console.log("coucou");
    return this.http.get(this.url+"/delete="+id);
  }

  findAllFromContributor(id:number):Observable<Project[]>{
    return this.http.get<Project[]>(this.url+"/user="+id);
  }

  findAllBackedByContributor(id:number):Observable<Project[]>{
    return this.http.get<Project[]>(this.url+"/backed/user="+id);
  }

  // Récupère les résultats de la recherche. str = string recherché, field et isDescending = filtres pour ordonner les résultats
  // ex : findAllWithSearch("jeu", "title", false) retourne tous les projets avec "jeu" dans le titre, classés selon leur titre (ordre ascendant)
  // ex de requête : http://localhost:8080/crow/projects/search=a/cat=9999/field=title/descending=false
  findAllWithSearch(str:string, field:string, isDescending:boolean, catId:number):Observable<Project[]>{
    return this.http.get<Project[]>(this.url+"/search="+str+"/cat="+catId+"/field="+field+"/descending="+isDescending);
  }
}

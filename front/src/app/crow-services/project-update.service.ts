import { Injectable } from '@angular/core';
import { ProjectUpdate } from '../models/ProjectUpdate';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectUpdateService {

  private url="http://localhost:8080/crow/projects/updates";

  constructor(private http:HttpClient) { }

  save(u:ProjectUpdate, projectId:number):Observable<ProjectUpdate>{
    return this.http.post<ProjectUpdate>(this.url+"/save/projId="+projectId, u);
  }
}

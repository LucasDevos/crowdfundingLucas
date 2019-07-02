import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../crow-services/category.service';
import { Category } from '../models/Category';
import { FormGroup, FormBuilder } from '@angular/forms';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { DataService } from '../crow-services/data.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories:Category[];
  categoryForm:FormGroup;
  colorForm:FormGroup;

  constructor(private categoryService:CategoryService, private fb:FormBuilder, private data:DataService) { }

  ngOnInit() {
    this.getCategories();
    this.initForm();
  }

  getCategories(){
    this.categoryService.findAll().subscribe(
      res=>{
        this.categories = res;
      }
    );
  }

  initForm(){
    this.categoryForm = this.fb.group({
      name : "",
      description : "",
      color : null
    });
    /*this.colorForm = this.fb.group({
      color : 'red'
    });*/
  }

  createCategorie(){
    //let col:Color = ""+this.colorForm.value;
    console.log("creer la categorie "+this.categoryForm.value);
    console.log(this.categoryForm.value.color);
    this.categoryService.save(this.categoryForm.value).subscribe(
      res => {
        this.getCategories();
        this.initForm();
      }
    );
  }

}

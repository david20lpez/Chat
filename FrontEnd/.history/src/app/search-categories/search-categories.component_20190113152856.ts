import { Component, OnInit } from '@angular/core';
import { Category } from '../category';
import { CategoryService } from '../category.service';

@Component({
  selector: 'search-categories',
  templateUrl: './search-categories.component.html',
  styleUrls: ['./search-categories.component.css']
})
export class SearchCategoriesComponent implements OnInit {

  name: string;
  categories: Category[];

  constructor(private dataService: CategoryService) { }

  ngOnInit() {
    this.name = "";
  }

  private searchCategories() {
    this.dataService.getCategoriesByName(this.name)
      .subscribe(categories => this.categories = categories);
  }

  onSubmit() {
    this.searchCategories();
  }
}
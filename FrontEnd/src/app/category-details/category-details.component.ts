import { Component, OnInit, Input } from '@angular/core';
import { CategoryService } from '../category.service';
import { Category } from '../category';

import { CategoryListComponent } from '../category-list/category-list.component';

@Component({
  selector: 'category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {

  @Input() category: Category;

  constructor(private categoryService: CategoryService, private listComponent: CategoryListComponent) { }

  ngOnInit() {
  }

  updateActive(isActive: boolean) {
    this.categoryService.updateCategory(this.category.id,
      { name: this.category.name})
      .subscribe(
        data => {
          console.log(data);
          this.category = data as Category;
        },
        error => console.log(error));
  }

  deleteCategory() {
    this.categoryService.deleteCategory(this.category.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
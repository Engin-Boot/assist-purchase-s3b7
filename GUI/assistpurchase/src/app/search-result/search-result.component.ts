import { Product } from './../shared/product';
import { Observable } from 'rxjs';
import { ProductService } from '../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent implements OnInit {

  products: Observable<Product[]>;
  mySubscription: any;
  constructor(private productService: ProductService,private route: ActivatedRoute, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
   }

  ngOnInit(): void {
    let observableStream = this.productService.getSearchedResult();
      observableStream.subscribe(
        (responseData: any)=>{
          this.products = responseData;
          console.log("hello");
          console.log(responseData);
        },
        (error)=>{
          console.log(error);
        },
          ()=>{
        console.log("Request compelted");
    })
}

ngOnDestroy() {
  if (this.mySubscription) {
    this.mySubscription.unsubscribe();
  }
}
}

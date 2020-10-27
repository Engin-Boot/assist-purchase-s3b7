import { Product } from './../shared/product';
import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-latestproduct',
  templateUrl: './latestproduct.component.html',
  styleUrls: ['./latestproduct.component.scss']
})
export class LatestproductComponent implements OnInit {
  products: Product[];

  constructor(private productService: ProductService,private route: ActivatedRoute, public router: Router) {
    this.productService.latest.subscribe(latest => {
      console.log("latest product");
      if(latest!=null){
        console.log(latest.slice(Math.max(latest.length-2,0)));
      this.products = latest.slice(Math.max(latest.length-2,0));
     // this.products = latest;
      }
      
  });
   }
  
  ngOnInit(): void {
  }


}

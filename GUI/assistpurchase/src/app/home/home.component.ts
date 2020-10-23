import { Observable } from 'rxjs';
import { ProductService } from '../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../shared/product';
import { Params, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  products: Observable<Product[]>;

  constructor(private productService: ProductService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log("i am exceucted");
    let observableStream = this.productService.getProducts();
    observableStream.subscribe(
      (responseData: any)=>{
        this.products = responseData;
      },
      (error)=>{
        console.log(error);
      },
        ()=>{
      console.log("Request compelted");
  })
  }

}

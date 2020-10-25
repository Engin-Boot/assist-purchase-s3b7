import { Observable } from 'rxjs';
import { Params, ActivatedRoute, Router} from '@angular/router';
import { Location } from '@angular/common';
import { ProductService } from './../services/product.service';
import { Product } from './../shared/product';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-productdetails',
  templateUrl: './productdetails.component.html',
  styleUrls: ['./productdetails.component.scss']
})
export class ProductdetailsComponent implements OnInit {

  product: Observable<Product>;
  productName:string
  id:number;

  constructor(private productService: ProductService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router) { }


  ngOnInit(): void {
    this.id = +this.route.snapshot.params['productId'];
    let observableStream = this.productService.getProductById(this.id);
    observableStream.subscribe(
      (responseData: any)=>{
        this.product = responseData;
        this.productName = responseData.productName;
      },
      (error)=>{
        console.log(error);
        this.router.navigate(['/pagenotfound']);
      },
        ()=>{
      console.log("Request compelted");
  })
  }

  onDelete(){
    let observableStream = this.productService.deleteProduct(this.id);
    observableStream.subscribe(
      (responseData: any)=>{
        this.product = responseData;
      },
      (error)=>{
        console.log(error);
      },
        ()=>{
      console.log("Request compelted");
      this.router.navigate(['/home']);
  })
  }

  createAlert(){
    let observableStream = this.productService.addAlert(this.id, this.productName);
    observableStream.subscribe(
      (responseData: any)=>{
        console.log(responseData);
      },
      (error)=>{
        console.log(error);
      },
        ()=>{
      console.log("Request compelted");
      alert("We will contact you Soon! Thanks for your Interest")
      this.router.navigate(['/home']);
  })
  }

  goBack(): void {
    this.location.back();
  }
}

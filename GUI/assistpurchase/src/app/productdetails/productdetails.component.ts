import { Observable } from 'rxjs';
import { Params, ActivatedRoute, Router, NavigationEnd} from '@angular/router';
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
  mySubscription: any;
  constructor(private productService: ProductService,
    private route: ActivatedRoute,
    private location: Location,
    private router: Router) {
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

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }
}

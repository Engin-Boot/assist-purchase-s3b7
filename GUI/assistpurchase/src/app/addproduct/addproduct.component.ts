import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { LatestproductComponent } from './../latestproduct/latestproduct.component';
import { ProductService } from './../services/product.service';
import { Product, Category} from './../shared/product';
import { Component, OnInit, ViewChild} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.scss']
})
export class AddproductComponent implements OnInit {

  productForm: FormGroup;
  product: Product;
  category = Category;

  constructor(private fb: FormBuilder, private productService: ProductService, private router: Router) { 
    this.createForm();
  }

  ngOnInit(): void {
  }

  createForm(){
    this.productForm = this.fb.group({
      id: 0,
      productName: '',
      category: 'Efficia',
      touchscreen: true,
      size: 9,
      portable:false,
    });
  }

  onSubmit(){
    this.product = this.productForm.value;
    console.log(this.product);
    let observableStream = this.productService.addProduct(this.product);

    observableStream.subscribe(
      (responseData: any)=>{
        console.log(responseData);
      },
      (error)=>{
        console.log(error);
      },
        ()=>{
      console.log("Request compelted");
    })
    alert("Product Added Successfully");
    this.productForm.reset({
      id: 0,
      productName: '',
      category: 'Efficia',
      touchscreen: false,
      size: 9,
      portable:false,
    });
    this.router.navigate(['/home']);
  }
}

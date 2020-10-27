import { Params, ActivatedRoute, Router} from '@angular/router';
import { ProductService } from './../services/product.service';
import { Product, Category} from './../shared/product';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.scss']
})
export class UpdateproductComponent implements OnInit {

  productForm: FormGroup;
  product: Product;
  category = Category;
  id:number;

  constructor(private fb: FormBuilder, private productService: ProductService, private route: ActivatedRoute, private router: Router) { 

  }


  ngOnInit(): void {
    this.id = +this.route.snapshot.params['productId'];
    console.log(this.id);
    let observableStream = this.productService.getProductById(this.id);
    observableStream.subscribe(
      (responseData: any)=>{
        this.product = responseData;
        console.log(responseData);
      },
      (error)=>{
        console.log(error);
        this.router.navigate(['/pagenotfound']);
      },
        ()=>{
      console.log("Request compelted");
      this.createForm();
  })
  }


  createForm(){
    this.productForm = this.fb.group({
      id: 0,
      productName: this.product.productName,
      category: this.product.category,
      touchscreen: this.product.touchscreen,
      size: this.product.size,
      portable:this.product.portable,
    });
  }

  onSubmit(){
    this.product = this.productForm.value;
    console.log(this.product);

    let observableStream = this.productService.updateProduct(this.product, this.id);

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

    alert("Product updated Successfully");
    this.router.navigate(['/home']);
  }

}

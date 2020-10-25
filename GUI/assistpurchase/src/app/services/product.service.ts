import { FormControl } from '@angular/forms';
import { PRODUCTS } from './../shared/mockProducts';
import { Product } from './../shared/product';
import { Alert } from './../shared/alert';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  usersUrl:string;
  httpClient: HttpClient;
  searchedresult:Observable<Product[]>;

  constructor(httpClient: HttpClient) { 
    this.httpClient = httpClient;
    this.usersUrl = 'http://localhost:8080/products';
  }

  // getProducts(){
  //   let observableStream = this.httpClient.get(`${this.usersUrl}/all`);
  //   return observableStream;
  //   }

    getProducts(): Observable<Product[]> {
      return this.httpClient.get<Product[]>(`${this.usersUrl}/all`);
    }

  // getProducts(): Product[]{
  //   return PRODUCTS;
  // }

  getProductById(id: number):Observable<Product> {
    return this.httpClient.get<Product>(`${this.usersUrl}/${id}`);
  }

  addProduct(product:Product):Observable<Product>{
    return this.httpClient.post<Product>(`${this.usersUrl}/add`, product);
  }

  updateProduct(product:Product, productId):Observable<Product>{
    return this.httpClient.put<Product>(`${this.usersUrl}/update/${productId}`, product);
  }

  deleteProduct(productId:number):Observable<Product>{
    return this.httpClient.delete<Product>(`${this.usersUrl}/delete/${productId}`);
  }

  addAlert(productId:number, productName: string): Observable<Alert>{
    const alert:Alert = {
      productId: productId.toString(),
      productName:productName,
      message:"Alert is created"
    }
    return this.httpClient.post<Alert>(`${this.usersUrl}/createAlert`,alert);
  }

  onProductFilter(formcontrolcategory:FormControl,formcontroltouchscreen:FormControl,
    formcontrolsize:FormControl, formcontrolportable:FormControl){
      const searchobj:any = {
        category:this.checkIfAny(formcontrolcategory),
        touchscreen:this.checkIfAny(formcontroltouchscreen),
        size:this.checkIfAny(formcontrolsize),
        portable:this.checkIfAny(formcontrolportable)
      }
    this.searchedresult = this.httpClient.post<any>(`${this.usersUrl}/search`,searchobj);   
  }

  getSearchedResult():Observable<Product[]>{
    return this.searchedresult;

  }

  checkIfAny(formcontrol:FormControl){
    if(formcontrol.value === 'Any'){
      return null;
    }
    return formcontrol.value;
  }
  
}

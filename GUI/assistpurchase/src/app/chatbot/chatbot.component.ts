import { Router } from '@angular/router';
import { ProductService } from './../services/product.service';
import { Component, OnInit } from '@angular/core';
import { Product, Category, BooleanOption, SIZE} from './../shared/product';
import { FormControl } from '@angular/forms';
@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.scss']
})
export class ChatbotComponent implements OnInit {

  categorylist = Category;
  booleanoption = BooleanOption;
  screensize = SIZE;

  selectedcategory = new FormControl();
  selectedportable = new FormControl();
  selectedtouchscreen = new FormControl();
  selectedsize = new FormControl();
  searchtext = new FormControl();

  checkcategory:string;
  checkportable:string;
  checktouchscreen:string;
  checksize:string;


  constructor(private productservice: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.checkcategory = 'category';
    this.checkportable = 'portable';
    this.checktouchscreen = 'touchscreen';
    this.checksize = 'screensize';
  }

  onclick(){
    let text = this.searchtext.value.toLowerCase();
    if(text == 'category' || text == 'portable' || text == 'touchscreen' || text == 'screensize'){
      console.log(this.searchtext.value);
      this.checkcategory = text;
      this.checkportable = text;
      this.checktouchscreen = text;
      this.checksize = text;
    }
    else{
      this.ngOnInit();
    }
  }

  onFilter(){
    this.productservice.onProductFilter(this.selectedcategory, this.selectedtouchscreen, this.selectedsize, this.selectedportable);
    this.router.navigate(['searchresult'])
  }



}

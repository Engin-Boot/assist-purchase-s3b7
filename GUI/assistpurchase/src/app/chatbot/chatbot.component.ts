import { ChatCategory, ChatPortable, ChatSize, ChatTouchScreen, Botreply } from './../shared/chatbotconst';
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

  chat:string;
  checkcategory:string;
  checkportable:string;
  checktouchscreen:string;
  checksize:string;

  checkchipcat = null;
  checkchipport = null;
  checkchipsize = null;
  checkchiptouch = null;
  


  constructor(private productservice: ProductService, public router: Router) { }

  ngOnInit(): void {
    this.chat = Botreply[0];
    this.checkcategory = 'category';
    this.checkportable = 'portable';
    this.checktouchscreen = 'touchscreen';
    this.checksize = 'screensize';
  }

  onclick(){
    let text = this.searchtext.value.toLowerCase();
    if(text!=null){
      this.chat = Botreply[1];
    }
    var userresp = text.split(' ');
    userresp.every(val => {if (ChatCategory.includes(val)){
      this.checkcategory = ChatCategory[0];
      this.chat += ChatCategory[0]+' ';
      return false;
    }else{
      this.checkcategory = null;
      return true;
    }
  });

    userresp.every(val => {if (ChatPortable.includes(val)){
      console.log("hi portable")
      this.checkportable = ChatPortable[0];
      this.chat += ChatPortable[0]+' ';
      return false;
    }else{
      this.checkportable = null;
      return true;
    }
  });

    userresp.every(val => {if (ChatSize.includes(val)){
      this.checksize = ChatSize[0];
      this.chat += ChatSize[0]+' ';
      return false;
    }else{
      this.checksize = null;
      return true;
    }
  });

    userresp.every(val => {if (ChatTouchScreen.includes(val)){
      this.checktouchscreen = ChatTouchScreen[0];
      this.chat += ChatTouchScreen[0]+' ';
      return false;
    }else{
      this.checktouchscreen = null;
      return true;
    }
  });


    if(this.checkInvalidSearchParameters()){
      this.ngOnInit();
      this.chat = Botreply[2];
    } 
  }

  checkInvalidSearchParameters(): boolean {
    return (this.checkcategory == null && this.checkportable == null && this.checksize == null && this.checktouchscreen==null);
  }

  onFilter(){
    this.productservice.onProductFilter(this.selectedcategory, this.selectedtouchscreen, this.selectedsize, this.selectedportable);
    this.router.navigate(['searchresult'])
  }

  changeChipCat(){
    this.checkchipcat = 'category';
  }

  changeChipPort(){
    this.checkchipport = 'portable';
  }

  changeChipSize(){
    this.checkchipsize = 'screensize';
  }

  changeChipTouch(){
    this.checkchiptouch = 'touchscreen';
  }

}

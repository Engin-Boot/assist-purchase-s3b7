import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{
  title = 'AssistPurchase';
  showchatbot:boolean;
  constructor(){
    this.showchatbot = false;
  }

  onclick(){
    if(this.showchatbot==false){
    this.showchatbot = true;
    }else{
      this.showchatbot = false;
    }
  }
}

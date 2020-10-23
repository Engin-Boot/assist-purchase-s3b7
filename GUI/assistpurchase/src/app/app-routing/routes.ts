import { PagenotfoundComponent } from './../pagenotfound/pagenotfound.component';
import { UpdateproductComponent } from './../updateproduct/updateproduct.component';
import { AddproductComponent } from './../addproduct/addproduct.component';
import { ContactComponent } from './../contact/contact.component';
import { Component } from '@angular/core';
import { ProductdetailsComponent } from './../productdetails/productdetails.component';
import { Routes } from '@angular/router';

// import { MenuComponent } from '../menu/menu.component';
import { HomeComponent } from '../home/home.component';
// import { AboutComponent } from '../about/about.component';
// import { ContactComponent } from '../contact/contact.component';


export const routes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'productdetails/:productId', component: ProductdetailsComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'addproduct', component: AddproductComponent},
    {path: 'updateproduct/:productId', component: UpdateproductComponent},
    {path: 'pagenotfound', component: PagenotfoundComponent},
    {path: '**', pathMatch:'full', component:PagenotfoundComponent}
];
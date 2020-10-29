import { SearchResultComponent } from './../search-result/search-result.component';
import { PagenotfoundComponent } from './../pagenotfound/pagenotfound.component';
import { UpdateproductComponent } from './../updateproduct/updateproduct.component';
import { AddproductComponent } from './../addproduct/addproduct.component';
import { ContactComponent } from './../contact/contact.component';
import { Component } from '@angular/core';
import { ProductdetailsComponent } from './../productdetails/productdetails.component';
import { Routes } from '@angular/router';

import { HomeComponent } from '../home/home.component';


export const routes: Routes = [
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'productdetails/:productId', component: ProductdetailsComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'addproduct', component: AddproductComponent},
    {path: 'updateproduct/:productId', component: UpdateproductComponent},
    {path: 'pagenotfound', component: PagenotfoundComponent},
    {path: 'searchresult', component: SearchResultComponent},
    {path: '**', redirectTo: '/pagenotfound', pathMatch: 'full'}
];
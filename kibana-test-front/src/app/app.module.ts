import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {AppSharedPipesModule} from "../app-core/pipe/app-core-pipe.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppSharedPipesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

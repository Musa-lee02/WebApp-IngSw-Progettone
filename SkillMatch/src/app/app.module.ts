import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import {TextFieldModule} from '@angular/cdk/text-field';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'; 
import {CdkMenuModule} from '@angular/cdk/menu';
import {MatFormFieldModule} from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InfoComponent } from './component/info/info.component';
import { AccediComponent } from './component/accedi/accedi.component';
import { ContattiComponent } from './component/contatti/contatti.component';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { CardsVetrinaComponent } from './cards-vetrina/cards-vetrina.component';
import { ServizioAnnunciService } from './servizio-annunci.service';

@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    AccediComponent,
    ContattiComponent,
    CardsVetrinaComponent,
  ],
  imports: [
    BrowserModule,
    TextFieldModule,CdkMenuModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatCardModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

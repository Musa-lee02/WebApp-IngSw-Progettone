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
import { CardsVetrinaComponent } from './component/cards-vetrina/cards-vetrina.component';
import { ServizioAnnunciService } from './service/servizio-annunci.service';
import { HomeComponent } from './component/home/home.component';
import {MatIconModule} from '@angular/material/icon'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EsploraComponent } from './component/esplora/esplora.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';


import {MatTooltipModule} from '@angular/material/tooltip';

import {MatSelectModule} from '@angular/material/select';
import {FormsModule} from '@angular/forms';
import { EffettuaAnnuncioComponent } from './component/effettua-annuncio/effettua-annuncio.component';
import { ProfiloComponent } from './component/profilo/profilo.component';
import { SceltaUtenteComponent } from './component/accedi/scelta-utente/scelta-utente.component';
import { ConfermaEmailComponent } from './component/accedi/conferma-email/conferma-email.component';
//import { CarouselComponent } from './carousel/carousel.component';
//import { SlickCarouselModule } from 'ngx-slick-carousel';

import { SlickCarouselModule } from 'ngx-slick-carousel';
import { ChatComponent } from './component/chat/chat.component';
import { FormAnnuncioComponent } from './component/effettua-annuncio/form-annuncio/form-annuncio.component';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { ChatTextComponent } from './component/chat/chat-text/chat-text.component';
import { RiepilogoDatiComponent } from './component/accedi/riepilogo-dati/riepilogo-dati.component';
import {HttpClientModule} from "@angular/common/http";


import { AsyncPipe } from '@angular/common';
import { RecuperoComponent } from './component/accedi/recupero/recupero.component';
import { RinserisciPasswordComponent } from './component/accedi/rinserisci-password/rinserisci-password.component';

@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    AccediComponent,
    ContattiComponent,
    CardsVetrinaComponent,
    HomeComponent,

    EsploraComponent,
       //EffettuaAnnuncioComponent,
       ProfiloComponent,
       SceltaUtenteComponent,
       ConfermaEmailComponent,
       EffettuaAnnuncioComponent,
       ChatComponent,
       FormAnnuncioComponent,
       NavBarComponent,
       ChatTextComponent,
       RiepilogoDatiComponent,


  ],
  imports: [
    BrowserModule,
    TextFieldModule,CdkMenuModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    FormsModule,
    MatAutocompleteModule,
    AsyncPipe,
    MatTooltipModule,
    MatIconModule,
    
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

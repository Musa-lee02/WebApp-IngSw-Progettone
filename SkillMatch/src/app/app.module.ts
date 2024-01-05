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
import { HomeComponent } from './component/home/home.component';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EsploraComponent } from './component/esplora/esplora.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';

import {MatSelectModule} from '@angular/material/select';
import {FormsModule} from '@angular/forms';
import { EffettuaAnnuncioComponent } from './component/effettua-annuncio/effettua-annuncio.component';
import { ProfiloComponent } from './component/profilo/profilo.component';
import { SceltaUtenteComponent } from './component/accedi/scelta-utente/scelta-utente.component';
import { ConfermaEmailComponent } from './component/accedi/conferma-email/conferma-email.component';
import { CarouselComponent } from './carousel/carousel.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    AccediComponent,
    ContattiComponent,
    CardsVetrinaComponent,
    HomeComponent,
  
    EsploraComponent,
       EffettuaAnnuncioComponent,
       ProfiloComponent,
       SceltaUtenteComponent,
       ConfermaEmailComponent,
       CarouselComponent,
    
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
    SlickCarouselModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

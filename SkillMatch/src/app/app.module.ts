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
import { RegisterComponent } from './component/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    InfoComponent,
    AccediComponent,
    ContattiComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    TextFieldModule,CdkMenuModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

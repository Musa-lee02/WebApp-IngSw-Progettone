import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServizioAnnunciService } from '../service/servizio-annunci.service';
import {SlickCarouselModule} from 'ngx-slick-carousel';
@Component({
  selector: 'app-carousel',
  standalone: true,
  imports: [CommonModule, SlickCarouselModule],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent implements OnInit {
  
  
  slides: any

  constructor(private service: ServizioAnnunciService){}
  
  
  ngOnInit(): void {
    
    this.slides=this.service.getAnnunci()
  }


  slideConfig= {
    "slidesToShow":4,
    "slidesToScroll":4,
    "autoplay": false,
    "autoplaySpeed": 5000,
    "pauseInHover": true,
    "infinite": true,
    "responsive":[
      {
        "breakpoint":992,
        "settings":{
          "arrows": true,
          "inifinite": true,
          "slidesToShow":3,
          "slidesToScroll":3
        }
      },
      {
        "breakpoint":768,
        "settings":{
          "arrows":true,
          "infinite":true,
          "slidesToShow":1,
          "slidesToScroll":1
        }

      }
    ]
  };
}

import {Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BackEndService } from "../../../service/BackEndService";

@Component({
  selector: 'app-conferma-email',
  templateUrl: './conferma-email.component.html',
  styleUrl: './conferma-email.component.css'
})
export class ConfermaEmailComponent implements OnInit {

  constructor(private route: ActivatedRoute, private service: BackEndService){}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      const username = params['username'];
      if(token){
        this.service.verifyToken(token, username);
      }
    })
  }
}

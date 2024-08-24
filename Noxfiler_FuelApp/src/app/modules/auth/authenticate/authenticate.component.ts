import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-authenticate',
  templateUrl: './authenticate.component.html',
  styleUrls: ['./authenticate.component.css']
})
export class AuthenticateComponent implements OnInit{

  constructor(private router : Router,private auth :AuthService){}
  ngOnInit(): void {
    this.authenticate();  
  }

  authenticate() { // change: do noop api call here
    const token = localStorage.getItem('AUTH_TOKEN');
    if (token) {
      this.auth.getNoop()
        .subscribe({
          next: () => {
            this.router.navigate(['vehicle']);
          },
          error: (error:any) => {
            const msg = error || `Unable to contact server!`;
            this.router.navigate(['login']);
          }
        })
    } else {
      this.router.navigate(['login']);
    }
  }
}

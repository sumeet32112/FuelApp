import { OnInit } from '@angular/core';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AuthService } from 'src/app/modules/auth/auth.service';
import { UserServiceService } from 'src/app/modules/services/user-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username = "username";
  @Input() menuIconView: boolean = false;
  @Output() toggleSideNavView = new EventEmitter<boolean>();
  about(){
  }
  logout(){
    this.authService.doLogout();
  }
  handleToggleMenu() {
    this.toggleSideNavView.emit(!this.menuIconView);
  }
  ngOnInit(): void {
    this.updateUser();
  }
  constructor( private user : UserServiceService, private authService : AuthService){}
  updateUser(){
    try{
      this.user.getUserByUsername().subscribe({
        next : res =>{
          if( res.success == true ){
            this.username = res.data;
          }else{
            console.log( res.message )
          }
        },
        error : err =>{

        }
      })
    }catch (error){
      console.log(error);
    }
  }
}


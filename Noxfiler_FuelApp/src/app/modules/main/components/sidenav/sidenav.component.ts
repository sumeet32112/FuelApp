import { Component, Input } from '@angular/core';
import { TokenService } from 'src/app/modules/auth/token.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent {

  @Input() menuIconView: boolean = false;
}

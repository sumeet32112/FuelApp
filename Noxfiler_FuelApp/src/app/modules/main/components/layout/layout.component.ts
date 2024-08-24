import { Component } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
  public iconView: boolean = false;
  handleMenuChange(value:boolean) {
    this.iconView = value;
  }
}

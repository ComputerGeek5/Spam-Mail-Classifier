import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  {
    path: '/inbox',
    title: 'Inbox',
    icon: 'icon-email-85',
    class: ''
  },
  {
    path: '/sent',
    title: 'Sent',
    icon: 'icon-send',
    class: ''
  },
  {
    path: '/read',
    title: 'Read',
    icon: 'icon-check-2',
    class: ''
  },
  {
    path: '/junk',
    title: 'Junk',
    icon: 'icon-trash-simple',
    class: ''
  }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() {}

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }
}

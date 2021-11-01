import { Component, OnInit } from '@angular/core';
import { OktaAuth, Tokens } from '@okta/okta-auth-js';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminPageComponent implements OnInit {
  isAuthenticated: boolean = false;
  fullName: string;
  storage: Storage = localStorage;

  constructor(private oktaAuth: OktaAuth) { }

  ngOnInit(): void {
    this.oktaAuth.authStateManager.subscribe(
      (result) => {
        this.isAuthenticated = result;
        this.getAdminDetails();
      }
    )
  }

  getAdminDetails() {
    if(this.isAuthenticated) {
      this.oktaAuth.getUser().then(
        (res) => {
          this.fullName = res.name!;
          const email = res.email;

          this.storage.setItem('userEmail', JSON.stringify(email));

        }
      )
    }
  }

  logout() {
    this.oktaAuth.signOut();
  }

}

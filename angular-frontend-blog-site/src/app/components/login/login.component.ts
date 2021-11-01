import { Component, OnInit } from '@angular/core';

import { OktaAuth, Tokens } from '@okta/okta-auth-js';
import OktaSignIn from '@okta/okta-signin-widget';

import blogSiteConfig from 'src/app/config/blog-site-config';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  oktaSignin: any;

  constructor(public oktaAuth: OktaAuth) {
    this.oktaSignin = new OktaSignIn({
      baseUrl: blogSiteConfig.oidc.issuer.split('/oauth2')[0],
      clientId: blogSiteConfig.oidc.clientId,
      redirectUri: blogSiteConfig.oidc.redirectUri,
      authParams: {
        pkce: true,
        issuer: blogSiteConfig.oidc.issuer,
        scopes: blogSiteConfig.oidc.scopes
      }
    })
   }

  ngOnInit(): void {
    this.oktaSignin.remove();

    this.oktaSignin.showSignInToGetTokens({
      el: '#sign-in-widget',
      scopes: blogSiteConfig.oidc.scopes
    }).then((tokens: Tokens) => {
      this.oktaSignin.remove();
      this.oktaAuth.handleLoginRedirect(tokens);
    }).catch((err: any) => {
      throw err;
    });
  }
}

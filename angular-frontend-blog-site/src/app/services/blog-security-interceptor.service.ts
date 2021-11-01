import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OktaAuth } from '@okta/okta-auth-js';
import { from, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

// Visitors were able to use an endpoint to manipulate the CRUD endpoints.
// Only authenticated users should be able to do that.
// Were able to DELETE, PUT, and POST information. 
export class BlogSecurityInterceptorService implements HttpInterceptor{

  // Requires the user to be authenticated in order to send information to the backend + database.
  // Must be signed into Okta
  constructor(private oktaAuth: OktaAuth) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(req, next));
  }

  // In order to send information, the frontend MUST send a token to show that they're legit
  // If not, it'll throw a 401 Unauthorized header
  private async handleAccess(req: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {
    // Get the endpoint that's currently being protected
    const securedEndpoints = [environment.baseUrl + 'blog'];

    if(securedEndpoints.some(url => req.urlWithParams.includes(url))) {
      // Get a token through oktaAuth
      const accessToken = await this.oktaAuth.getAccessToken();

      // Clone the request and send over the access token
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + accessToken
        }
      });
    }

    return next.handle(req).toPromise();
  }
}

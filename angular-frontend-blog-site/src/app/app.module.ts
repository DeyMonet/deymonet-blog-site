import { Injector, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { TopicComponent } from './components/topic/topic.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostsComponent } from './components/posts/posts.component';

import blogSiteConfig from './config/blog-site-config';

import { OKTA_CONFIG, OktaAuthModule, OktaCallbackComponent, OktaAuthGuard } from '@okta/okta-angular';
import { LoginComponent } from './components/login/login.component';
import { OktaAuth } from '@okta/okta-auth-js';
import { PostService } from './services/post.service';
import { AdminPageComponent } from './components/adminpage/adminpage.component';
import { CreatePostsComponent } from './components/create-posts/create-posts.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewPostsComponent } from './components/view-posts/view-posts.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BlogSecurityInterceptorService } from './services/blog-security-interceptor.service';

const oktaAuth = new OktaAuth(blogSiteConfig.oidc);
const routes: Routes = [
  // With OktaAuthGuard
  { path: 'view-posts', component: ViewPostsComponent, canActivate: [ OktaAuthGuard ] },
  { path: 'create-post', component: CreatePostsComponent, canActivate: [ OktaAuthGuard ] },
  { path: 'admin', component: AdminPageComponent, canActivate: [ OktaAuthGuard ] },

  // With OktaCallBack
  { path: 'login/callback', component: OktaCallbackComponent },
  { path: 'login', component: LoginComponent },

  { path: "topic/:topicName", component: PostListComponent },
  { path: "post/:title", component: PostsComponent },
  { path: "about", component: AboutComponent },
  { path: "home", component: HomeComponent},

  { path: '', redirectTo: "/home", pathMatch: 'full' },
  {path: "**", redirectTo: "/home", pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    HomeComponent,
    TopicComponent,
    PostListComponent,
    PostsComponent,
    LoginComponent,
    AdminPageComponent,
    CreatePostsComponent,
    ViewPostsComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule, 
    OktaAuthModule,
    ReactiveFormsModule,
    NgbModule,
    FormsModule
  ],

  providers: [PostService, 
              { 
                provide: OKTA_CONFIG, 
                useValue: { 
                  oktaAuth,
                  onAuthRequired: (oktaAuth: OktaAuth, injector: Injector) => {
                    const router = injector.get(Router);
                    router.navigate(['/login']);
                  }
                }
              },
              {
                provide: HTTP_INTERCEPTORS, 
                useClass: BlogSecurityInterceptorService, 
                multi: true
              }
            ],
  bootstrap: [AppComponent]
})
export class AppModule { }

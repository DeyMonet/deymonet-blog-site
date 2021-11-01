import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from '../common/post';

@Injectable({
  providedIn: 'root'
})
export class PostCrudService {
  private postCrudUrl = environment.baseUrl + 'blog/';

  constructor(private http: HttpClient) { }

  // Create new Post
    createPost(post: Post): Observable<any> {
    return this.http.post(`${this.postCrudUrl}create`, post);
  }

  // Update existing Post
  updatePost(post: Post, id: number): Observable<any> {
    return this.http.put(`${this.postCrudUrl}update/${id}`, post);
  }

  // Delete existing Post
  deletePost(id: number): Observable<any> {
    return this.http.delete(`${this.postCrudUrl}delete/${id}`)
  }
}

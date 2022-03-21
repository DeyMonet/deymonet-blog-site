import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Post } from '../common/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private postUrl = environment.baseUrl + 'posts';

  constructor(private http: HttpClient) { }

  // For pagination and search
  getPostsPagination(topicName: string,
                      page: number,
                      size: number): Observable<GetResponsePost> {
    const postPageUrl = 
            `${this.postUrl}/search/findByTopicTag_TopicName_OrderByPublishedDesc?topicName=${topicName}&page=${page}&size=${size}`;
    return this.http.get<GetResponsePost>(postPageUrl);
  }
  
  getPostsByTopicTag(topicName: string): Observable<Post[]> {
    const postTopic = 
            `${this.postUrl}/search/findByTopicTagOrderByPublishedAsc?topicName=${topicName}`;
    
    // this requires to be mapped to the response interface in order for it to obtain the correct data
    // if this is not present, then the array will be empty and nothing will show on the screen.
    return this.http.get<GetResponsePost>(postTopic).pipe(map(response => response._embedded.posts));
  }

  getFirstFivePosts(): Observable<Post[]> {
    const postTopic = `${this.postUrl}/search/findFirst5ByOrderByPublishedDesc`;
    return this.http.get<GetResponsePost>(postTopic).pipe(map(Response => Response._embedded.posts));
  }

  getPosts(): Observable<Post[]> {
    return this.http.get<GetResponsePost>(this.postUrl).pipe(map(response => response._embedded.posts));
  }

  getPost(title: string): Observable<Post> {
    const postTitleUrl = `${this.postUrl}/search/findByTitle?title=${title}`;
    return this.http.get<Post>(postTitleUrl);
  }
}

interface GetResponsePost {
  _embedded: {
    posts: Post[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number
  }
}
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Topic } from '../common/topic';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private topicUrl =  environment.baseUrl + 'topic';

  constructor(private http: HttpClient) { }

  getTopics(): Observable<Topic[]> {
    return this.http.get<GetResponseTopic>(this.topicUrl).pipe(map(response => response._embedded.topic));
  }
}

interface GetResponseTopic {
  _embedded: {
    topic: Topic[];
  }
}

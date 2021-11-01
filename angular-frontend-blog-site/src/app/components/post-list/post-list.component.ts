import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/common/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  posts: Post[] = [];

  postTopicName: string = '';
  pageNumber: number = 1;
  pageSize: number = 10;
  totalElements: number = 0;

  constructor(private postService: PostService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.showPostsFromTopic();
    })
  }

  showPostsFromTopic() {
    const topicTag: boolean = this.route.snapshot.paramMap.has('topicName');

    if(topicTag) {
      this.postTopicName = this.route.snapshot.paramMap.get('topicName')!;
    } else {
      this.postTopicName = '';
    }

    this.postService.getPostsByTopicTag(this.postTopicName).subscribe(data => {
      console.log(data);
      this.posts = data;
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/common/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  post: Post = new Post();
  
  constructor(private postService: PostService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.showCurrentPost()
    });
  }

  showCurrentPost() {
    const postTitle = this.route.snapshot.paramMap.get('title')!;
    
    this.postService.getPost(postTitle).subscribe((data) => {
      console.log(data);
      this.post = data;
    });
  }
}

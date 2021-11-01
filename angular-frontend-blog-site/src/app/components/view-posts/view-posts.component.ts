import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Post } from 'src/app/common/post';
import { Topic } from 'src/app/common/topic';
import { PostCrudService } from 'src/app/services/post-crud.service';
import { PostService } from 'src/app/services/post.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-view-posts',
  templateUrl: './view-posts.component.html',
  styleUrls: ['./view-posts.component.css']
})
export class ViewPostsComponent implements OnInit {
  post: Post = new Post();
  posts: Post[];
  topics: Topic[];

  updatePostFormGroup: FormGroup;

  constructor(private postCrudService: PostCrudService,
              private postService: PostService,
              private topicService: TopicService,
              private route: ActivatedRoute,
              private router: Router,
              private modalService: NgbModal,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.viewAllPosts();
      this.topicService.getTopics().subscribe(data => {
        this.topics = data;
      })
    })

    this.updatePostFormGroup = this.formBuilder.group({
      title: new FormControl('', [Validators.required]),
      content: new FormControl('', [Validators.required]),
    });
  }

  // ng-template 
  open(updatePostForm) {
    this.modalService.open(updatePostForm, {ariaLabelledBy: 'modal-basic-title', size: 'lg'});
  }

  viewAllPosts() {
    this.postService.getPosts().subscribe(data => {
      console.log(JSON.stringify(data));

      this.posts = data;
    })
  }

  // Form builder usage here
  updatePost(id: any) {

    this.post.title = this.updatePostFormGroup.get('title')?.value;
    this.post.content = this.updatePostFormGroup.get('content')?.value;

    console.log(`title=${this.post.title}, content=${this.post.content}`);

    this.postCrudService.updatePost(this.post, id).subscribe( {
      next: response => {
        alert('Post has been updated!');

        this.router.navigateByUrl("/home");
      },
      error: err => {
        alert(`Whoops! Something went wrong here: ${err.message}`);
      }
    })
  }

  deletePost(id: any) {
    this.postCrudService.deletePost(id).subscribe({
      next: response => {
        alert('Post has been deleted!');

        this.router.navigateByUrl("/home");
      },
      error: err => {
        alert(`Whoops! There's an issue here: ${err.message}`);
      }
    })
  }

}

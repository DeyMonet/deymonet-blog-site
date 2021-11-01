import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/common/post';
import { Topic } from 'src/app/common/topic';
import { PostCrudService } from 'src/app/services/post-crud.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-create-posts',
  templateUrl: './create-posts.component.html',
  styleUrls: ['./create-posts.component.css']
})
export class CreatePostsComponent implements OnInit {
  post: Post = {
    title: '',
    content: '',
    topicTag: ''
  };

  topics: Topic[];
  createPostForm: FormGroup;

  constructor(private postCrudService: PostCrudService,
              private topicService: TopicService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.topicService.getTopics().subscribe(data => {
        this.topics = data;
      })
    })
    
    // Form Builder
    this.createPostForm = this.formBuilder.group({
        title: new FormControl('', [Validators.required]),
        content: new FormControl('', [Validators.required]),
        topicTag: new FormControl('', [Validators.required])
    });
  }

  get title() {return this.createPostForm.get('post.title')}
  get content() {return this.createPostForm.get('post.content')}
  get topicTag() {return this.createPostForm.get('post.topicTag')}

  get topicName() {return this.createPostForm.get('topicName')}

  onSubmit() {
    if(this.createPostForm.invalid) {
      this.createPostForm.markAllAsTouched();
      return;
    }

    let createNewPost = new Post();

    this.post.title = this.createPostForm.get('title')?.value;
    this.post.content = this.createPostForm.get('content')?.value;
    this.post.topicTag = this.createPostForm.get('topicTag')?.value;    

    createNewPost.title = this.post.title;
    createNewPost.content = this.post.content;
    createNewPost.topicTag = this.post.topicTag;

    console.log('post=' + JSON.stringify(this.post));
    console.log(`Title=${createNewPost.title}, Content=${createNewPost.content}, TopicTag=${createNewPost.topicTag}`);

    this.postCrudService.createPost(createNewPost).subscribe({
      next: response => {
        alert('Post has been created!');

        this.router.navigateByUrl("/home");
      },
      error: err => {
        alert(`Whoops! There's an issue here: ${err.message}`);
      }
    })
  }
}

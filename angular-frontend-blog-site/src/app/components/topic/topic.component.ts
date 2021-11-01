import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Topic } from 'src/app/common/topic';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {
  topic: Topic[] = [];

  constructor(private topicService: TopicService,
              private router: Router) { }

  ngOnInit(): void {
    this.showTopics();
  }

  showTopics() {
    this.topicService.getTopics().subscribe((data) => {
      this.topic = data;
    })
  }
}

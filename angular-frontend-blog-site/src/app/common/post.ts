import { Topic } from "./topic";

export class Post {
    id?: number;
    postId?: string;
    title?: string;
    content?: string;
    published?: Date;
    lastUpdated?: Date;
    topicTag?: String;
}

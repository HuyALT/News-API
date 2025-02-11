import { User } from "./user";

export interface NewsComment{
    id: number;
    newsId: number;
    userId: number;
    content: string;
    createAt: string;
    updateAt: string;

    userInfo: User;
}
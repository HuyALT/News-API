export interface news {
    id: number;
    title: String;
    image: string;
    type: number;
    link: string;
    summary: string;
    sort_title: string;
    content: string;
    user_id: number;
    active: number;
    category_id: number;
    subcategory_id: number;
    create_at: string;
    update_at: string;

    category_name: string;
    subcategory_name: string;
}
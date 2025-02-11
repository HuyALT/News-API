import { subcategory } from "./subcategory";

export interface Category {
    id: number;
    name: string;
    sort_name: string;
    subcategories: subcategory[];
}

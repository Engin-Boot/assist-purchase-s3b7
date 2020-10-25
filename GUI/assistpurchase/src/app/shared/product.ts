export interface Product {
    id: number;
    productName: string;
    category: string;
    touchscreen: boolean;
    size: number;
    portable:boolean;
}

export const Category = ['Any','Efficia','Intellivue','Goldway'];

export const BooleanOption = ['Any', true, false];

export const SIZE = ['Any', 7, 8 , 9 , 10 , 11, 12];
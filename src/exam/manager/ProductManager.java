package exam.manager;

import exam.model.Product;

public class ProductManager {

    public static final int TOTAL_CATEGORIES = 5; // số danh mục
    public static final int MAX_PER_CATEGORY = 20; // số product tối đa trong mỗi danh mục

    public static Product[][] data = new Product[TOTAL_CATEGORIES][MAX_PER_CATEGORY];
    public static int[] counts = new int[TOTAL_CATEGORIES];

}


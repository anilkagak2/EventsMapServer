package server;

/* Category bean class. 
 * Getters & Setters for the bean class 
 * */
public class Category {
	public int categoryId;		// Category Id to map this name
	public String category;		// Category name
	
	public int getCategoryId (){
		return categoryId;
	}
	
	public String getCategory (){
		return category;
	}
}

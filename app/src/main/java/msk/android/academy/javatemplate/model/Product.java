package msk.android.academy.javatemplate.model;

public class Product {
	private String code;
	private String price;
	private String name;
	private String description;
	private String status;
	private int count=1;

	public Product(String code, String price, String name) {
		this.code = code;
		this.price = price;
		this.name = name;

	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Product{" +
			"code = '" + code + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

package catalyst.thymeleaf.entity;

public class User {

	private String name;

	private Integer age;

	public User(String name, Integer age) {

		this.name = name;
		this.age = age;
	}

	public User(String name) {

		this.name = name;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {

		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {

		this.age = age;
	}
	
	@Override
	public String toString() {
		
		return name;
	}

}

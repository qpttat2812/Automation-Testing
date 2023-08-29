package learn_java;

//exercise array - topic 07

public class Student {
	String name;
	int age;
	double score;
	
	public Student(String name, int age, double score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}
	
	public void displayed() {
		System.out.println("Name is " + name);
		System.out.println("Age is " + age);
		System.out.println("Score is " + score);
	}
	
	public static void main(String[] args) {
		Student[] students = new Student[3];
		students[0] = new Student("Alex", 20, 5.6);
		students[1] = new Student("Ashley", 10, 8.6);
		students[2] = new Student("Kim", 19, 4.6);
		
		for(int i = 0; i < students.length; i++) {
			students[i].displayed();
		}
	}
}

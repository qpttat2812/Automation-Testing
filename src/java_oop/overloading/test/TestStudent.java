package java_oop.overloading.test;

import java_oop.overloading.model.Student;

public class TestStudent {

	public static void main(String[] args) {
		Student student1 = new Student(1, "Abc");
		Student student2 = new Student(2, "xxxx", 5);
		Student student3 = new Student();
		
		student3.evaluateScore();
		
		student1.evaluateScore(6, 1);
		
	}

}

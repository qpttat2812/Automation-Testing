package java_oop;

public class Run_Student {

	public static void main(String[] args) {
		Student student1 = new Student(1, "Ashley", 6.7, 9);
		Student student2 = new Student(2, "Bros", 4, 3);
		Student student3 = new Student(3, "Kenny", 9, 4);
		
		student1.displayStudentInfo();
		student2.displayStudentInfo();
		student3.displayStudentInfo();
	}

}

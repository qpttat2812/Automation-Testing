package java_oop.overloading.model;

public class Student {
	private int studentCode;
	private String studentName;
	private double score;
	
	//overloading Constructor
	public Student() {
		
	}
	
	public Student(int studentCode) {
		this();
		this.studentCode = studentCode;
	}
	
	public Student(int studentCode, String studentName) {
		this(studentCode);
		this.studentName = studentName;
	}
	
	public Student(int studentCode, String studentName, double score) {
		this(studentCode, studentName);
		this.score = score;
	}
	
	//overloading Method
	public void evaluateScore() {
		if (this.score > 5) {
			System.out.println("Passed");
		}
		else {
			System.out.println("Failed");
		}
	}
	
	public void evaluateScore(double score, int priorityArea) {
		System.out.println("Your score is " + score + " and priority area is " + priorityArea);
	}
}

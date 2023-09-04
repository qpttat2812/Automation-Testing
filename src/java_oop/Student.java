package java_oop;

public class Student {
	private int studentCode;
	private String studentName;
	private double conceptScore;
	private double practicalScore;
	
	public Student(int studentCode, String studentName, double conceptScore, double practicalScore){
		this.studentCode = studentCode;
		this.studentName = studentName;
		this.conceptScore = conceptScore;
		this.practicalScore = practicalScore;
	}
	
	
	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}

	public int getStudentCode() {
		return studentCode;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setConceptScore(double conceptScore) {
		this.conceptScore = conceptScore;
	}
	
	public double getConceptScore() {
		return conceptScore;
	}
	
	public void setPracticalScore(double practicalScore) {
		this.practicalScore = practicalScore;
	}
	
	public double getPracticalScore() {
		return practicalScore;
	}
	
	public double calculateMeanScore(double conceptualScore, double practicalScore) {
		return (conceptualScore + practicalScore * 2)/3;
	}
	
	public void displayStudentInfo() {
		System.out.println("Student code: " + studentCode);
		System.out.println("Student name: " + studentName);
		System.out.println("Mean score = " + calculateMeanScore(conceptScore, practicalScore));
	}
}

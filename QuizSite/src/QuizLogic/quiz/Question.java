package QuizLogic.quiz;

import java.util.List;

public class Question {
	public int id;
	public String description;
	public List<Answer> answers;
	public int quiz_id;
	@Override
	public String toString() {
		return "Question [id=" + id + ", desription=" + description
				+ ", answers=" + answers + "]";
	}
	
}

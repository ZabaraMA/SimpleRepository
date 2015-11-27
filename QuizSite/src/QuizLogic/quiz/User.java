package QuizLogic.quiz;

import QuizLogic.dao.TopicDao;
//import dao.UserDao;

public class User {
	public int id;
	public String name;
	public String login;
	public String password;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login
				+ ", password=" + password + "]";
	}
	
	/*public static void main(String[] args) throws Exception {
		
		QuizLogic.dao.TopicDao topicDao = new QuizLogic.dao.TopicDao();
		System.out.println(topicDao);
		
	}*/
	
	
}




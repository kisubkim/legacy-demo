package courseservice.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import courseservice.course.Course;

public class User {

	private List<Course> courses = new ArrayList<>();
	private List<User> friends = new ArrayList<>();
	
	public List<User> getFriends() {
		return friends;
	}
	
	public void addFriend(User user) {
		friends.add(user);
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public List<Course> courses() {
		return Collections.unmodifiableList(courses);
	}

	public boolean isFriendWith(User aUser) {
		// Replace algorithm
		return friends.contains(aUser);
	}

}

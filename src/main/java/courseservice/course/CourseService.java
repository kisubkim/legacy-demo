package courseservice.course;

import java.util.List;

import courseservice.exceptions.UserNotLoggedInException;
import courseservice.user.User;

// GRASP pattern
// Cyclomatic Complexity = 5, Threshold = 10
public class CourseService {
	private ICourseDAO dao;
	
	public CourseService(ICourseDAO dao) {
		super();
		this.dao = dao;
	}

	public List<Course> getCoursesByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		if (loggedInUser == null) 
			throw new UserNotLoggedInException();
		
		return user.isFriendWith(loggedInUser) ? 
				coursesBy(user) : emptyList();
	}

	private List<Course> emptyList() {
		return java.util.Collections.emptyList();
	}

	protected List<Course> coursesBy(User user) {
		return dao.coursesBy(user);
	}

}
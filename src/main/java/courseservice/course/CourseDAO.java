package courseservice.course;

import java.util.List;

import courseservice.exceptions.CollaboratorCallException;
import courseservice.user.User;

public class CourseDAO implements ICourseDAO {

	public List<Course> coursesBy(User user) {
		throw new CollaboratorCallException(
				"CourseDAO should not be invoked on an unit test.");
	}
	
}
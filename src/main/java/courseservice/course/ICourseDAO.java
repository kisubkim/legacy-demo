package courseservice.course;

import java.util.List;

import courseservice.user.User;

//@FunctionalInterface
public interface ICourseDAO {
	List<Course> coursesBy(User user);
	// ...
}

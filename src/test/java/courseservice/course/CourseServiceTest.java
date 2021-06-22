package courseservice.course;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import courseservice.exceptions.UserNotLoggedInException;
import courseservice.user.User;

class CourseServiceTest {
	// SUT
	private CourseService courseService;

	private static final Course PATTERNS = new Course();
	private static final Course REFACTORINGS = new Course();
	private static final User UNUSED_USER = null;
	private static final User GUEST = null;
	private static final User ANOTHER_USER = new User();
	private static final User REGISTERED_USER = new User();
	
	@Mock
	private ICourseDAO dao;
	
	@BeforeEach
	void init() {
//		dao = new ICourseDAO() {
//			@Override
//			public List<Course> coursesBy(User user) {
//				return user.courses();
//			}
//		};
		
//		dao = User::courses;
		MockitoAnnotations.openMocks(this);
		
		courseService = new CourseService(User::courses);
	}

	@Test
	void should_throw_exception_if_there_is_no_loggedin_user() {
		// Given (Arrange)

		// When (Act)
		// Then (Assert)
		assertThrows(UserNotLoggedInException.class, () -> {
			courseService.getCoursesByUser(UNUSED_USER, GUEST);
		});
	}

	@Test
	void should_return_empty_list_if_user_and_loggedin_user_are_not_friends() {
		// Given (Arrange)
		// Fluent API
		User user = UserBuilder.of()
				.withCourses(PATTERNS, REFACTORINGS)
				.withFriends(ANOTHER_USER)
				.build();
		
		// When (Act)
		List<Course> courses = courseService.getCoursesByUser(user, REGISTERED_USER);

		// Then (Assert)
		assertEquals(0, courses.size());

	}

	@Test
	void should_return_nonempty_course_list_if_they_are_friends() {
		// Given (Arrange)
		when(dao.coursesBy(isA(User.class))).thenReturn(List.of(PATTERNS, REFACTORINGS));
		
		User user = UserBuilder.of()
				.withCourses(PATTERNS, REFACTORINGS)
				.withFriends(ANOTHER_USER, REGISTERED_USER)
				.build();
		
		// When (Act)
		List<Course> courses = courseService.getCoursesByUser(user, REGISTERED_USER);

		// Then (Assert)
		assertEquals(2, courses.size());

	}

	private static class UserBuilder {
		private Course[] courses = new Course[0];
		private User[] friends = new User[0];

		public static UserBuilder of() {
			return new UserBuilder();
		}

		public User build() {
			User user = new User();
			Arrays.stream(courses).forEach(user::addCourse);
			Arrays.stream(friends).forEach(user::addFriend);
			return user;
		}

		public UserBuilder withFriends(User...friends) {
			this.friends = friends;
			return this;
		}

		public UserBuilder withCourses(Course...courses) {
			this.courses = courses;
			return this;
		}
		
	}
	
}

package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import moviebuddy.Movie;


/**
 * @author springrunner.kr@gmail.com
 */

public class MovieFinderTest {
	@Test
	public void MovieFinderTest() {
		MovieFinder movieFinder = new MovieFinder();
		List<Movie> result = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3, result.size());

        result = movieFinder.releasedYearBy(2015);
        Assertions.assertEquals(225, result.size());
	}
	
	
}

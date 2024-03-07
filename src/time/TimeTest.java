package time;
// 3600 seconds in hour
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05:00");
				assertTrue("The seconds were not calculated properly", seconds==18305);
	}

	@Test
	void testGetTotalSecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {Time.getTotalSeconds("10:00");});
	}

	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("23:59:59:00");
		assertTrue("The seconds were not calculated properly", seconds==86399);
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:19:10:01", "06:30:10:02", "07:00:10:03" })
	void testGetSecondsGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("Seconds were not calculated correctly", seconds == 10);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {Time.getSeconds("10:00");});
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:09:59:99", "06:15:59:66", "07:50:59:77" })
	void testGetSecondsBoundary(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("Seconds were not calculated correctly", seconds == 59);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "05:15:50:12", "06:15:49:24", "07:15:39:36" })
	void testGetTotalMinutesGood(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("Minutes were not calculated correctly", minutes == 15);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {Time.getTotalMinutes("10:AG:SS");});
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:59:50:22", "06:59:40:55", "07:59:00:11" })
	void testGetTotalMinutesBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("Minutes were not calculated correctly", minutes == 59);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "15:40:50:04", "15:50:09:06", "15:00:19:05" })
	void testGetTotalHoursGood(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("Hours were not calculated correctly", hours == 15);
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {Time.getTotalHours("SA:50:50");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "23:59:59:84", "23:39:59:32", "23:59:20:49" })
	void testGetTotalHoursBoundary(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("Hours were not calculated correctly", hours == 23);
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:19:10:11", "06:30:10:11", "07:00:10:11" })
	void testGetMillisecondsGood(String candidate) {
		int milliseconds = Time.getMilliseconds(candidate);
		assertTrue("Seconds were not calculated correctly", milliseconds == 11);
	}
	
	@Test
	void testGetMillisecondsBad() {
		assertThrows(
				NumberFormatException.class,
				()-> {Time.getMilliseconds("10:00");});
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:09:59:00", "06:15:59:99", "07:50:59:01" })
	void testGetMillisecondsBoundary(String candidate) {
		int milliseconds = Time.getMilliseconds(candidate);
		assertTrue("Seconds were not calculated correctly", milliseconds == 99 || milliseconds == 00 || milliseconds == 01);
	}
	
}

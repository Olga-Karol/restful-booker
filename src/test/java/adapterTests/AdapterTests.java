package adapterTests;

import model.Booking;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AdapterTests extends BaseTest {

    @Test
    public void getTokenTest(){
        assertThat(http.auth.getToken(), allOf(notNullValue(), hasLength(15)));
    }

    @Test
    public void createBookingTest(){
        int bookingId = createTestBooking();
        assertThat(bookingId, greaterThan(10));
        Booking actualBooking = http.bookingId.getBookingById(bookingId);
        assertThat(actualBooking, equalTo(new Booking()));
    }

    @Test
    public void getBookingIdsTest(){
        assertThat(http.booking.getBookingIds(),greaterThanOrEqualTo(1));
    }

    @Test
    public void updateBookingTest(){
        int bookingId = createTestBooking();
        Booking oldBooking = http.bookingId.getBookingById(bookingId);
        Booking updatedBooking = http.bookingId.getUpdatedBooking();
        assertThat(http.bookingId.updateBookingById(bookingId, getToken()), allOf(not(oldBooking),equalTo(updatedBooking)));
    }


    @Test
    public void partialUpdateBookingTest(){
        int bookingId = createTestBooking();
        Booking oldBooking = http.bookingId.getBookingById(bookingId);
        Booking updatedBooking = http.bookingId.partialUpdateBookingById(bookingId, getToken());
        assertThat(updatedBooking, not(oldBooking));
    }


    @Test
    public void deleteBookingTest(){
        int bookingId = createTestBooking();
        assertThat(http.bookingId.deleteBooking(bookingId, getToken()), containsString("Created"));
        assertThat(http.bookingId.getDeletedBookingById(bookingId), containsString("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void healthCheckTest(){
        assertThat(http.ping.ping(), containsString("Created"));
    }

    private String getToken(){
        return http.auth.getToken();
    }


    private int createTestBooking(){
        return http.booking.createBooking(new Booking());
    }
}

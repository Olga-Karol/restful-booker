package adapter;


import constants.Endpoints;
import model.Booking;
import model.Bookingdates;
import model.UpdatedParams;


public class BookingIdAdapter extends BaseAdapter {




    public BookingIdAdapter() {
        super(Endpoints.BOOKING_ID_URN);
    }

    public Booking getBookingById(int id) {
        bookingUrnConf(Endpoints.BOOKING_ID_URN, id);
        return get()
                .extract()
                .body()
                .as(Booking.class);
    }

    public String deleteBooking(int id, String token) {
        bookingUrnConf(Endpoints.BOOKING_ID_URN, id);
        return delete(token)
                .extract()
                .statusLine();
    }

    public String getDeletedBookingById(int id) {
        bookingUrnConf(Endpoints.BOOKING_ID_URN, id);
        return notFound()
                .extract()
                .statusLine();
    }

    public Booking updateBookingById(int id, String token) {
        bookingUrnConf(Endpoints.BOOKING_ID_URN, id);
        return put(getUpdatedBooking(),token)
                .extract()
                .body()
                .as(Booking.class);
    }

    public Booking partialUpdateBookingById(int id, String token) {
        bookingUrnConf(Endpoints.BOOKING_ID_URN, id);
        String paramsJson = gson.toJson(new UpdatedParams("New firstname", "New lastname"));
        return patch(paramsJson,token)
                .extract()
                .body()
                .as(Booking.class);
    }

    public Booking getUpdatedBooking() {
        Booking updatedBooking = new Booking();
        updatedBooking.setFirstName("Peter");
        updatedBooking.setLastName("Peterson");
        updatedBooking.setDepositPaid(false);
        updatedBooking.setTotalPrice(2500);
        updatedBooking.setAdditionalNeeds("Sauna");
        Bookingdates newDates = new Bookingdates();
        newDates.setCheckin("2022-04-25");
        newDates.setCheckout("2022-10-20");
        updatedBooking.setBookingdates(newDates);
        return updatedBooking;
    }

}

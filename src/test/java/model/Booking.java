package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Builder.Default
    @SerializedName("firstname")
    @Expose
    public String firstName = "volha";
    @Builder.Default
    @SerializedName("lastname")
    @Expose
    public String lastName = "karol";
    @Builder.Default
    @SerializedName("totalprice")
    @Expose
    public int totalPrice = 400;
    @Builder.Default
    @SerializedName("depositpaid")
    @Expose
    public boolean depositPaid = true;
    @Builder.Default
    public Bookingdates bookingdates = new Bookingdates();
    @Builder.Default
    @SerializedName("additionalneeds")
    @Expose
    public String additionalNeeds = "breakfast";
}
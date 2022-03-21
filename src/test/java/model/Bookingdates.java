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
public class Bookingdates {

@SerializedName("checkin")
@Expose
@Builder.Default
public String checkin = "2022-01-01";
@SerializedName("checkout")
@Expose
@Builder.Default
public String checkout = "2022-04-01";

}
package adapter;

import constants.Endpoints;
import model.User;

public class HealthCheckAdapter extends BaseAdapter{

    public HealthCheckAdapter() {
        super(Endpoints.PING_URN);
    }

    public String ping() {
        return created()
                .extract()
                .statusLine();
    }
}

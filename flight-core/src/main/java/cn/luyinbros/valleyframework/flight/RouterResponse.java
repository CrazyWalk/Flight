package cn.luyinbros.valleyframework.flight;

public class RouterResponse {
   private RouteRequest request;


    public RouterResponse(RouteRequest request) {
        this.request = request;
    }


    public RouteRequest getRequest() {
        return request;
    }


}

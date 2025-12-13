package gj.avengers.demo.response;

public record CurrentLocation(
        double latitude,
        double longitude
) {

    public static CurrentLocation of(double latitude, double longitude) {
        return new CurrentLocation(latitude, longitude);
    }
}

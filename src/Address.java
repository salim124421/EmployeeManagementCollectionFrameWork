
class Address {
    private final String id;
    private  String city;
    private  String state;
    private  String country;
    private  String localAddress;

    public Address(String id, String city, String state, String country, String localAddress) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.localAddress = localAddress;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getCountry() {
        return country;
    }
    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", localAddress='" + localAddress + '\'' +
                '}';
    }
}

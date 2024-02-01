import java.util.Comparator;
class AddressComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        Address address1 = emp1.getAddress();
        Address address2 = emp2.getAddress();
        int cityComparison = address1.getCity().compareTo(address2.getCity());
        if (cityComparison != 0) {
            return cityComparison;
        }
        int stateComparison = address1.getState().compareTo(address2.getState());
        if (stateComparison != 0) {
            return stateComparison;
        }
        return address1.getCountry().compareTo(address2.getCountry());
    }
}








package bg.softuni._15_modelmapper;

import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.DestinationSetter;
import org.modelmapper.spi.Mapping;
import org.modelmapper.spi.PropertyInfo;
import org.modelmapper.spi.SourceGetter;
import org.modelmapper.spi.TypeSafeSourceGetter;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();

        Address address = new Address("Bg", "Stara Zagora");
        Person person = new Person("First", "Last", "2020-02-04", address);

        PropertyMap<Person, PersonInfoDTO> personToInfoDTOMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setBirthdate(source.getBirthday());
            }
        };
        modelMapper.addMappings(personToInfoDTOMap);
        PersonInfoDTO dto = modelMapper.map(person, PersonInfoDTO.class);

        TypeMap<PersonInfoDTO, Person> dtoToPersonMap =
                modelMapper.createTypeMap(PersonInfoDTO.class, Person.class);
        dtoToPersonMap.addMappings(
            mapping -> mapping.map(
                source -> source.getBirthdate(),
                Person::setBirthday
            )
        );
//        dtoToPersonMap.addMapping(src -> src.getBirthdate(), Person::setBirthday);
//        dtoToPersonMap.addMapping(PersonInfoDTO::getBirthdate, Person::setBirthday);

        Person fromMap = dtoToPersonMap.map(dto);
        Person personFromDTO = modelMapper.map(dto, Person.class);

        System.out.println();
    }
}

class PersonInfoDTO {
    private String firstName;
    private String birthdate;
    private String addressCity;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
}

class Person {
    private String firstName;
    private String lastName;
    private String birthday;
    private Address address;

    public Person() {}

    public Person(String firstName, String lastName, String birthday, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address {
    private String country;
    private String city;

    public Address() {}

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
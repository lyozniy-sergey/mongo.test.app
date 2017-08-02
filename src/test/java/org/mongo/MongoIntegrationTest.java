package org.mongo;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongo.config.MongoTestConfig;
import org.mongo.model.Address;
import org.mongo.model.Contact;
import org.mongo.model.Sequence;
import org.mongo.services.AddressService;
import org.mongo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author lyozniy.sergey on 31 Jul 2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoTestConfig.class})
@ActiveProfiles({"test"})
public class MongoIntegrationTest {
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ContactService contactService;

    @Autowired
    private AddressService addressService;

    private Contact contact;
    private List<Contact> contacts;

    private static Map<String, Integer> contactAggregations() {
        return new HashMap<String, Integer>() {
            {
                put("Adam", 2);
                put("Serena", 1);
                put("Bob", 1);
                put("Venera", 1);
                put("Roger", 1);
                put("Roy", 1);

                put("Patterson", 1);
                put("Williams", 2);
                put("Fisher", 1);
                put("Federrer", 1);
                put("Ranger", 1);
                put("Jons", 2);
                put("Williams", 2);
            }
        };
    }

//    public static Collection<Contact> contacts() {
//        return Arrays.asList(
//                new Contact("Adam", "Patterson", "2345", "adam@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Ukraine");
//                                setCity("Kiev");
//                                setState("Kievskiy");
//                                setStreet("Mazepa");
//                                setHouse("145a");
//                                setApartment(11);
//                            }
//                        });
//                    }
//                },
//                new Contact("Serena", "Williams", "1234", "serena@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Ukraine");
//                                setCity("Kiev");
//                                setState("Kievskiy");
//                                setStreet("Mazepa");
//                                setHouse("145a");
//                                setApartment(11);
//                            }
//                        });
//                    }
//                },
//                new Contact("Bob", "Fisher", "4721", "bob@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Ukraine");
//                                setCity("Kiev");
//                                setState("Kievskiy");
//                                setStreet("Malitenka");
//                                setHouse("11");
//                                setApartment(5);
//                            }
//                        });
//                    }
//                },
//                new Contact("Venera", "Williams", "5432", "venera@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Ukraine");
//                                setCity("Lvov");
//                                setState("Lvovskaya");
//                                setStreet("Malitenka");
//                                setHouse("11");
//                                setApartment(5);
//                            }
//                        });
//                    }
//                },
//                new Contact("Roger", "Federrer", "8482", "roger@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Ukraine");
//                                setCity("Lvov");
//                                setState("Lvovskaya");
//                                setStreet("Malitenka");
//                                setHouse("11");
//                                setApartment(5);
//                            }
//                        });
//
//                    }
//                },
//                new Contact("Adam", "Jons", "6384", "gary@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Germany");
//                                setCity("Munchin");
//                                setState("Bavarskiy");
//                                setStreet("Otto");
//                                setHouse("55");
//                                setApartment(78);
//                            }
//                        });
//                    }
//                },
//                new Contact("Roy", "Jons", "7290", "roy@com.ua") {
//                    {
//                        setAddress(new Address() {
//                            {
//                                setCountry("Spain");
//                                setCity("Barselona");
//                                setState("Barselonskaya");
//                                setStreet("Paolo");
//                                setHouse("68b");
//                                setApartment(9);
//                            }
//                        });
//                    }
//                }
//        );
//    }

    @Before
    public void setUp() throws Exception {
        contacts = createContacts();
//        contact = new Contact("Tom", "Galasky", "1831", "tom@com.ua");
        contact = contacts.get(0);
    }

    private List<Contact> createContacts() {
        final List<Address> addresses = createAddresses();
        Contact contact = new Contact("Adam", "Patterson", "2345", "adam@com.ua");
        contact.setAddress(addresses.get(0));
        return Collections.singletonList(contact);

//        return Arrays.asList(
//                new Contact("Adam", "Patterson", "2345", "adam@com.ua") {
//                    {
//                        setAddress(addresses.get(0));
//                    }
//                },
//                new Contact("Serena", "Williams", "1234", "serena@com.ua") {
//                    {
//                        setAddress(addresses.get(0));
//                    }
//                },
//                new Contact("Bob", "Fisher", "4721", "bob@com.ua") {
//                    {
//                        setAddress(addresses.get(1));
//                    }
//                },
//                new Contact("Venera", "Williams", "5432", "venera@com.ua") {
//                    {
//                        setAddress(addresses.get(1));
//                    }
//                },
//                new Contact("Roger", "Federrer", "8482", "roger@com.ua") {
//                    {
//                        setAddress(addresses.get(2));
//
//                    }
//                },
//                new Contact("Adam", "Jons", "6384", "gary@com.ua") {
//                    {
//                        setAddress(addresses.get(3));
//                    }
//                },
//                new Contact("Roy", "Jons", "7290", "roy@com.ua") {
//                    {
//                        setAddress(addresses.get(4));
//                    }
//                }
//        );
    }

    private List<Address> createAddresses() {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setCity("Kiev");
        address.setState("Kievskiy");
        address.setStreet("Mazepa");
        address.setHouse("145a");
        address.setApartment(11);
        return Collections.singletonList(address);
//        return Arrays.asList(
//                new Address() {
//                    {
//                        setCountry("Ukraine");
//                        setCity("Kiev");
//                        setState("Kievskiy");
//                        setStreet("Mazepa");
//                        setHouse("145a");
//                        setApartment(11);
//                    }
//                },
//                new Address() {
//                    {
//                        setCountry("Ukraine");
//                        setCity("Kiev");
//                        setState("Kievskiy");
//                        setStreet("Malutenka");
//                        setHouse("11");
//                        setApartment(5);
//                    }
//                },
//                new Address() {
//                    {
//                        setCountry("Ukraine");
//                        setCity("Lvov");
//                        setState("Lvovskaya");
//                        setStreet("Malitenka");
//                        setHouse("11");
//                        setApartment(5);
//                    }
//                },
//                new Address() {
//                    {
//                        setCountry("Germany");
//                        setCity("Munchin");
//                        setState("Bavarskiy");
//                        setStreet("Otto");
//                        setHouse("55");
//                        setApartment(78);
//                    }
//                },
//                new Address() {
//                    {
//                        setCountry("Spain");
//                        setCity("Barselona");
//                        setState("Barselonskaya");
//                        setStreet("Paolo");
//                        setHouse("68b");
//                        setApartment(9);
//                    }
//                }
//        );
    }

    @After
    public void tearDown() throws Exception {
        mongoOperations.dropCollection(Contact.class);
        mongoOperations.dropCollection(Address.class);
        mongoOperations.updateFirst(Query.query(Criteria.where("id").is("contacts")), Update.update("sequence", 0L), Sequence.class);
        mongoOperations.updateFirst(Query.query(Criteria.where("id").is("address")), Update.update("sequence", 0L), Sequence.class);
    }

    @Test
    public void checkMongoTemplate() {
        assertNotNull(mongoOperations);
        DBCollection createdCollection = mongoOperations.createCollection(Contact.COLLECTION_NAME);
        assertTrue(mongoOperations.collectionExists(Contact.COLLECTION_NAME));
        assertEquals(0, createdCollection.count());
    }

    @Test
    public void testSave() {
        contactService.add(contact);
        assertTrue("Expected only one added contact", contactService.getAll().size() == 1);
        assertTrue("Expected only one contact with id " + contact.getId(), contactService.get(contact.getId()) != null);
    }

    @Test
    public void testFindByName() {
        contactService.add(contact);
        List<Contact> matches = contactService.getByPattern(contact.getName());
        assertEquals("Expected only one contact " + matches.size(), 1, matches.size());
    }

    @Test
    public void testFindByLastName() {
        contactService.add(contact);
        assertEquals("Expected only one contact", 1, contactService.getByPattern(contact.getLastName()).size());
    }

    @Test
    public void testUpdateLastName() {
        contactService.add(contact);
        assertEquals("Expected only one contact", 1, contactService.getByPattern(contact.getLastName()).size());

        String oldLastName = contact.getLastName();
        String updatedLastName = "UpdatedLastName";
        contact.setLastName(updatedLastName);
        contactService.update(contact);

        assertEquals("Expected only one contact with updated last name", 1, contactService.getByPattern(updatedLastName).size());
        assertEquals("Expected zero contact with old last name", 0, contactService.getByPattern(oldLastName).size());
    }

    @Test
    public void testRemoveContact() {
        contactService.add(contact);
        assertTrue("Expected only one contact", contactService.get(contact.getId()) != null);

        contactService.remove(contact.getId());

        assertTrue("Expected zero contact", contactService.get(contact.getId()) == null);
    }

    @Test
    public void testSaveContactsWithAddresses() {
        contacts.forEach(c -> contactService.add(c));
        assertTrue("Expected only one added contact", contactService.getAll().size() == 1);
        assertTrue("Expected only one contact with id " + contact.getId(), contactService.get(contact.getId()) != null);
        assertEquals("Expected 5 unique addresses" + contact.getId(), 5, addressService.getAll().size());
    }

    @Test
    public void testAggregationByName() {
        testAggregationBy("name", "count");
    }

    @Test
    public void testAggregationByLastName() {
        testAggregationBy("lastName", "count");
    }

    public void testAggregationBy(String field, String alias) {
        contacts.forEach(c -> contactService.add(c));

        assertEquals(format("Expected %s contacts", contacts.size()), contacts.size(), contactService.getCount());

        Map<String, Integer> ca = contactAggregations();

        List<DBObject> aggregationByField = contactService.getAggregationByField(field, alias);
        aggregationByField.forEach(o ->
        {
            String name = o.get(field).toString();
            Integer count = ca.get(name);
            assertEquals(format("Expected %s of %s", count, name), count, o.get(alias));
        });
    }
}

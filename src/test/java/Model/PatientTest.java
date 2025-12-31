package Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PatientTest {

    @Test
    public void testPatientGettersAndSetters() {
        Patient patient = new Patient();
        patient.setFname("John");
        patient.setLname("Doe");
        patient.setGender("Male");
        patient.setAge("30");
        patient.setCity("New York");
        patient.setEmail("john.doe@example.com");
        patient.setAddress("123 Main St");
        patient.setMobile("1234567890");

        assertEquals("John", patient.getFname());
        assertEquals("Doe", patient.getLname());
        assertEquals("Male", patient.getGender());
        assertEquals("30", patient.getAge());
        assertEquals("New York", patient.getCity());
        assertEquals("john.doe@example.com", patient.getEmail());
        assertEquals("123 Main St", patient.getAddress());
        assertEquals("1234567890", patient.getMobile());
    }
}

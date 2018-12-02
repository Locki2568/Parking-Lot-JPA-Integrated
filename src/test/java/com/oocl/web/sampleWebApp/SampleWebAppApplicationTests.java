package com.oocl.web.sampleWebApp;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.oocl.web.sampleWebApp.WebTestUtil.getContentAsObject;
import static com.oocl.web.sampleWebApp.WebTestUtil.toObject;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SampleWebAppApplicationTests {
    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    //@Autowired
    //private EntityManager entityManager;

    @Autowired
    private MockMvc mvc;

	@Test
    public void should_get_parking_boys() throws Exception {
        // Given
        final ParkingBoy boy = parkingBoyRepository.save(new ParkingBoy("boy"));

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkingboys"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingBoyResponse[] parkingBoys = getContentAsObject(result, ParkingBoyResponse[].class);

        assertEquals(1, parkingBoys.length);
        assertEquals("boy", parkingBoys[0].getEmployeeId());
    }

    @Test
    public void should_post_append_parking_boy_to_repo() throws Exception {
        // Given
        String json = "{\"employeeId\" : \"test123\"}";

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkingboys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        // Then
        assertEquals(201, result.getResponse().getStatus());

        List<ParkingBoy> parkingBoys = parkingBoyRepository.findAll();

        // Should not use getOne() because it only get a proxy object from cache
        // Should use findOne() instead

        Optional<ParkingBoy> actualBoy = parkingBoyRepository.findById(1L);

        final ParkingBoyResponse parkingBoy = ParkingBoyResponse.create(actualBoy.get());

        assertEquals(1, parkingBoys.size());
        assertEquals("test123", parkingBoy.getEmployeeId());
    }

    @Test
    public void hould_throws_exception_when_employeeID_lenth_too_long(){
        //Given A parking boy with employeeID too long
        final String employeeID = "IdThatISTooLongggggggggggggggggggggggggggggggggg";
        ParkingBoy parkingBoy = new ParkingBoy(employeeID);
        //When save into repository should throw exception
        AssertHelper.assertThrows(Exception.class, () ->{
            parkingBoyRepository.save(parkingBoy);
            parkingBoyRepository.flush();
        });
    }

    @Test
    public void should_get_parking_lots() throws Exception {
        // Given
        final ParkingLot parkinglot = parkingLotRepository.save(new ParkingLot("TestParkID", 10));

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/parkinglots"))
                .andReturn();

        // Then
        assertEquals(200, result.getResponse().getStatus());

        final ParkingLotResponse[] parkingLots = getContentAsObject(result, ParkingLotResponse[].class);

        assertEquals(1, parkingLots.length);
        assertEquals("TestParkID", parkingLots[0].getParkingLotID());
        assertEquals(10, parkingLots[0].getCapacity());
    }

    @Test
    public void should_post_append_parking_lot_to_DB() throws Exception {
        // Given
        String json = "{\"parkingLotID\" : \"TestPark123\", \"capacity\" : 10}";

        // When
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn();

        // Then
        assertEquals(201, result.getResponse().getStatus());

        List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        // Should not use getOne() because it only get a proxy object from cache
        // Should use findOne() instead

        Optional<ParkingLot> actualParkingLot = parkingLotRepository.findById(1L);

        final ParkingLotResponse parkingLot = ParkingLotResponse.create(actualParkingLot.get());

        assertEquals(1, parkingLots.size());
        assertEquals("TestPark123", parkingLot.getParkingLotID());
        assertEquals(10, parkingLot.getCapacity());
    }
}

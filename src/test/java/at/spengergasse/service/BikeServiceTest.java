package at.spengergasse.service;

import org.junit.jupiter.api.Test;



class BikeServiceTest {

    @Test
    void test_toString_should_work(){
        BikeService bikeService = new BikeService();
        bikeService.testDaten(10);
        System.out.println(bikeService);
    }

}
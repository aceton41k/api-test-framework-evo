package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.vehicle_info.VehicleInfoResponse;
import com.example.api.egov.services.VehicleInfoService;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class VehicleInfoTests extends EGovApiBaseTest {

    @Autowired
    VehicleInfoService vehicleInfoService;

    @Test
    
    @DisplayName("[/vehicleInfo/byPinfl][POST] Получить данные о машине по Pinfl")
    void getVehicleInfoByPinfl() {
        step("Отправить запрос на получение данных о машине", () -> {
            var request = Map.of("pPinpp", "12345678901234");
            var response = vehicleInfoService.getVehicleInfoByPinfl(request, VehicleInfoResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getPResult()).as("pResult").isEqualTo(1);
                soft.assertThat(response.getPComment()).as("pComment").isEqualTo("Ok.");
                soft.assertThat(response.getPPinpp()).as("pPinpp").isEqualTo("12345678901234");
                soft.assertThat(response.getPOwner()).as("pOwner").isEqualTo("Eshmatov Ali Vali O’g’li");
                soft.assertThat(response.getPOwnerType()).as("pOwnerType").isEqualTo(0);
                soft.assertThat(response.getPOrganizationInn()).as("pOrganizationInn").isNull();

                soft.assertThat(response.getVehicle()).as("vehicle list").hasSize(1);

                var vehicle = response.getVehicle().getFirst();

                soft.assertThat(vehicle.getPRegistrationDate()).as("pRegistrationDate").isEqualTo("27.06.2022");
                soft.assertThat(vehicle.getPDateSchetSpravka()).as("pDateSchetSpravka").isEqualTo("26.06.2022");
                soft.assertThat(vehicle.getPTuningGivenDate()).as("pTuningGivenDate").isNull();
                soft.assertThat(vehicle.getPTuningIssueDate()).as("pTuningIssueDate").isNull();
                soft.assertThat(vehicle.getPVehicleId()).as("pVehicleId").isEqualTo(1001);
                soft.assertThat(vehicle.getPTexpassportSery()).as("pTexpassportSery").isEqualTo("ABA");
                soft.assertThat(vehicle.getPTexpassportNumber()).as("pTexpassportNumber").isEqualTo("1234567");
                soft.assertThat(vehicle.getPPlateNumber()).as("pPlateNumber").isEqualTo("01X123BA");
                soft.assertThat(vehicle.getPModel()).as("pModel").isEqualTo("MATIZ");
                soft.assertThat(vehicle.getPVehicleColor()).as("pVehicleColor").isEqualTo("СИНИЙ");
                soft.assertThat(vehicle.getPDivision()).as("pDivision").isEmpty();
                soft.assertThat(vehicle.getPYear()).as("pYear").isEqualTo(2012);
                soft.assertThat(vehicle.getPVehicleType()).as("pVehicleType").isEqualTo(2);
                soft.assertThat(vehicle.getPKuzov()).as("pKuzov").isEqualTo("XABCDEFGH1234567");
                soft.assertThat(vehicle.getPShassi()).as("pShassi").isEqualTo("РАКАМСИЗ");
                soft.assertThat(vehicle.getPFullWeight()).as("pFullWeight").isEqualTo(0);
                soft.assertThat(vehicle.getPEmptyWeight()).as("pEmptyWeight").isEqualTo(0);
                soft.assertThat(vehicle.getPMotor()).as("pMotor").isEqualTo("ZXC9876543");
                soft.assertThat(vehicle.getPFuelType()).as("pFuelType").isEqualTo(4);
                soft.assertThat(vehicle.getPSeats()).as("pSeats").isEqualTo(4);
                soft.assertThat(vehicle.getPStands()).as("pStands").isEqualTo(0);
                soft.assertThat(vehicle.getPComments()).as("pComments").isNull();
                soft.assertThat(vehicle.getPPower()).as("pPower").isEqualTo(64);
                soft.assertThat(vehicle.getPTuningPermit()).as("pTuningPermit").isEqualTo("0");

                // prev* должны быть null
                soft.assertThat(vehicle.getPrevPnfl()).as("prevPnfl").isNull();
                soft.assertThat(vehicle.getPrevOwner()).as("prevOwner").isNull();
                soft.assertThat(vehicle.getPrevOwnerType()).as("prevOwnerType").isNull();
                soft.assertThat(vehicle.getPrevPlateNumber()).as("prevPlateNumber").isNull();
                soft.assertThat(vehicle.getPrevTexpasportSery()).as("prevTexpasportSery").isNull();
                soft.assertThat(vehicle.getPrevTexpasportNumber()).as("prevTexpasportNumber").isNull();
            });
        });
    }

    @Test
    
    @DisplayName("[/vehicleInfo/byPlateNumber][POST] Получить данные о машине по plateNumber")
    void getVehicleInfoByPlateNumber() {
        step("Отправить запрос на получение данных о машине", () -> {
            var request = Map.of("pPlateNumber", "01A123BC");
            var response = vehicleInfoService.getVehicleByPlateNumber(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getPResult()).as("pResult").isEqualTo(1);
                soft.assertThat(response.getPComment()).as("pComment").isEqualTo("Ok.");
                soft.assertThat(response.getPPinpp()).as("pPinpp").isEqualTo("12345678901234");
                soft.assertThat(response.getPOwner()).as("pOwner").isEqualTo("Eshmatov Ali Vali O’g’li");
                soft.assertThat(response.getPOwnerType()).as("pOwnerType").isEqualTo(0);
                soft.assertThat(response.getPOrganizationInn()).as("pOrganizationInn").isNull();

                soft.assertThat(response.getVehicle()).as("vehicle list").hasSize(1);

                var vehicle = response.getVehicle().getFirst();

                soft.assertThat(vehicle.getPRegistrationDate()).as("pRegistrationDate").isEqualTo("25.12.2020");
                soft.assertThat(vehicle.getPDateSchetSpravka()).as("pDateSchetSpravka").isEqualTo("22.12.2020");
                soft.assertThat(vehicle.getPTuningGivenDate()).as("pTuningGivenDate").isNull();
                soft.assertThat(vehicle.getPTuningIssueDate()).as("pTuningIssueDate").isNull();
                soft.assertThat(vehicle.getPVehicleId()).as("pVehicleId").isEqualTo(62462);
                soft.assertThat(vehicle.getPTexpassportSery()).as("pTexpassportSery").isEqualTo("AAF");
                soft.assertThat(vehicle.getPTexpassportNumber()).as("pTexpassportNumber").isEqualTo("5775775");
                soft.assertThat(vehicle.getPPlateNumber()).as("pPlateNumber").isEqualTo("01A123BC");
                soft.assertThat(vehicle.getPModel()).as("pModel").isEqualTo("COBALT");
                soft.assertThat(vehicle.getPVehicleColor()).as("pVehicleColor").isEqualTo("СЕРЕБРИСТЫЙ");
                soft.assertThat(vehicle.getPDivision()).as("pDivision").isEmpty();
                soft.assertThat(vehicle.getPYear()).as("pYear").isEqualTo(2020);
                soft.assertThat(vehicle.getPVehicleType()).as("pVehicleType").isEqualTo(2);
                soft.assertThat(vehicle.getPKuzov()).as("pKuzov").isEqualTo("ABCD1234567DEF");
                soft.assertThat(vehicle.getPShassi()).as("pShassi").isEqualTo("РАКАМСИЗ");
                soft.assertThat(vehicle.getPFullWeight()).as("pFullWeight").isEqualTo(0);
                soft.assertThat(vehicle.getPEmptyWeight()).as("pEmptyWeight").isEqualTo(0);
                soft.assertThat(vehicle.getPMotor()).as("pMotor").isEqualTo("DEF1234567XYZ");
                soft.assertThat(vehicle.getPFuelType()).as("pFuelType").isEqualTo(4);
                soft.assertThat(vehicle.getPSeats()).as("pSeats").isEqualTo(0);
                soft.assertThat(vehicle.getPStands()).as("pStands").isEqualTo(0);
                soft.assertThat(vehicle.getPComments()).as("pComments").isNull();
                soft.assertThat(vehicle.getPPower()).as("pPower").isEqualTo(106);
                soft.assertThat(vehicle.getPTuningPermit()).as("pTuningPermit").isEqualTo("0");

                // prev* поля должны быть null
                soft.assertThat(vehicle.getPrevPnfl()).as("prevPnfl").isNull();
                soft.assertThat(vehicle.getPrevOwner()).as("prevOwner").isNull();
                soft.assertThat(vehicle.getPrevOwnerType()).as("prevOwnerType").isNull();
                soft.assertThat(vehicle.getPrevPlateNumber()).as("prevPlateNumber").isNull();
                soft.assertThat(vehicle.getPrevTexpasportSery()).as("prevTexpasportSery").isNull();
                soft.assertThat(vehicle.getPrevTexpasportNumber()).as("prevTexpasportNumber").isNull();
            });
        });
    }

    @Test
    
    @DisplayName("[/vehicleInfo/byTin][POST] Получить данные о машине по tin")
    void getVehicleInfoByTin() {
        step("Отправить запрос на получение данных о машине", () -> {
            var request = Map.of("pTin", "123456789");
            var response = vehicleInfoService.getVehicleInfoByTin(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getPResult()).as("pResult").isEqualTo(1);
                soft.assertThat(response.getPComment()).as("pComment").isEqualTo("Ok.");
                soft.assertThat(response.getPPinpp()).as("pPinpp").isNull();
                soft.assertThat(response.getPOwner()).as("pOwner").isEqualTo("OOO \"Transit Logistics\"");
                soft.assertThat(response.getPOwnerType()).as("pOwnerType").isEqualTo(1);
                soft.assertThat(response.getPOrganizationInn()).as("pOrganizationInn").isEqualTo("123456789");

                soft.assertThat(response.getVehicle()).as("vehicle list").hasSize(1);

                var v = response.getVehicle().getFirst();

                soft.assertThat(v.getPRegistrationDate()).as("pRegistrationDate").isEqualTo("12.03.2021");
                soft.assertThat(v.getPDateSchetSpravka()).as("pDateSchetSpravka").isEqualTo("10.03.2021");
                soft.assertThat(v.getPTuningGivenDate()).as("pTuningGivenDate").isNull();
                soft.assertThat(v.getPTuningIssueDate()).as("pTuningIssueDate").isNull();

                soft.assertThat(v.getPVehicleId()).as("pVehicleId").isEqualTo(501);
                soft.assertThat(v.getPTexpassportSery()).as("pTexpassportSery").isEqualTo("ACB");
                soft.assertThat(v.getPTexpassportNumber()).as("pTexpassportNumber").isEqualTo("7654321");
                soft.assertThat(v.getPPlateNumber()).as("pPlateNumber").isEqualTo("40T555AA");
                soft.assertThat(v.getPModel()).as("pModel").isEqualTo("GAZELLE");
                soft.assertThat(v.getPVehicleColor()).as("pVehicleColor").isEqualTo("БЕЛЫЙ");
                soft.assertThat(v.getPDivision()).as("pDivision").isEmpty();
                soft.assertThat(v.getPYear()).as("pYear").isEqualTo(2021);
                soft.assertThat(v.getPVehicleType()).as("pVehicleType").isEqualTo(6);
                soft.assertThat(v.getPKuzov()).as("pKuzov").isEqualTo("GAZ1234567890");
                soft.assertThat(v.getPShassi()).as("pShassi").isEqualTo("РАКАМСИЗ");
                soft.assertThat(v.getPFullWeight()).as("pFullWeight").isEqualTo(3500);
                soft.assertThat(v.getPEmptyWeight()).as("pEmptyWeight").isEqualTo(2100);
                soft.assertThat(v.getPMotor()).as("pMotor").isEqualTo("GZL9876543");
                soft.assertThat(v.getPFuelType()).as("pFuelType").isEqualTo(3);
                soft.assertThat(v.getPSeats()).as("pSeats").isEqualTo(3);
                soft.assertThat(v.getPStands()).as("pStands").isEqualTo(0);
                soft.assertThat(v.getPComments()).as("pComments").isNull();
                soft.assertThat(v.getPPower()).as("pPower").isEqualTo(110);
                soft.assertThat(v.getPTuningPermit()).as("pTuningPermit").isEqualTo("0");

                soft.assertThat(v.getPrevPnfl()).as("prevPnfl").isNull();
                soft.assertThat(v.getPrevOwner()).as("prevOwner").isNull();
                soft.assertThat(v.getPrevOwnerType()).as("prevOwnerType").isEqualTo(1);
                soft.assertThat(v.getPrevPlateNumber()).as("prevPlateNumber").isNull();
                soft.assertThat(v.getPrevTexpasportSery()).as("prevTexpasportSery").isNull();
                soft.assertThat(v.getPrevTexpasportNumber()).as("prevTexpasportNumber").isNull();
            });
        });
    }

    // TODO надо будет написать каждому урлу 500 проверки и пустые значения

}

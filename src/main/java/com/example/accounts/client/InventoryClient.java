package com.example.accounts.client;


import com.example.accounts.dto.MedicineResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="inventoryClient",url="http://10.177.68.40:8080")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, path="medicine/getMedicineList")
    List<MedicineResponseDto> getMedicineList ();
}

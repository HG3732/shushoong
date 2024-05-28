package kh.mclass.shushoong.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelListController {

	
	@GetMapping("/hotel/list")
	public String hotelList() {
		return "hotel/hotel_list";
	}
}

package kh.mclass.shushoong.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.service.HotelService;

@Controller
public class HotelController {

	@Autowired
	private HotelService service;
	
	@GetMapping("/hotel/main")
	public String hotelMain() {
		return "hotel/hotel_main";
	}
	
	@GetMapping("/hotel/list")
	public String hotelList() {
		return "hotel/hotel_list";
	}
	
	@GetMapping("/hotel/view")
	public String hotelview(Model model) {
		
		List<HotelRoomDto> result = service.selectRoomList("2OS001");
		
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i).getRoomCat().equals("0")) {
				result.get(i).setRoomCat("스탠다드 룸");
			} else if(result.get(i).getRoomCat().equals("1")) {
				result.get(i).setRoomCat("디럭스 룸");
			} else if(result.get(i).getRoomCat().equals("2")) {
				result.get(i).setRoomCat("슈페리어 룸");
			} else if(result.get(i).getRoomCat().equals("3")) {
				result.get(i).setRoomCat("스위트 룸");
			} else {
				result.get(i).setRoomCat("기타");
			}
		}
		model.addAttribute("roomlist", result);
		
		
		model.addAttribute("piclist", service.selectPicList("2OS001"));
		model.addAttribute("dtolist", service.selectAllHotelList("2OS001")); 
		return "hotel/hotel_view";
	}
	
}
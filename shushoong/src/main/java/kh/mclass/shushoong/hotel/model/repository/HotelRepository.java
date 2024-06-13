package kh.mclass.shushoong.hotel.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.hotel.model.domain.HotelPic;
import kh.mclass.shushoong.hotel.model.domain.HotelDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelFacilityDtoRes;
import kh.mclass.shushoong.hotel.model.domain.HotelRoomDto;
import kh.mclass.shushoong.hotel.model.domain.HotelSearchDtoRes;

@Mapper
public interface HotelRepository {
	
	public List<HotelDtoRes> selectAllHotelList(String code, String people, String keyword, String maxPrice, String sortBy, String sortTo);
	
	public List<HotelPic> selectHotelPicList(String hotelCode);
	
	public List<HotelRoomDto> selectRoomList(String hotelCode);
	
	public List<HotelSearchDtoRes> selectHotelSearchList(String hotelCode);
	
	public List<HotelFacilityDtoRes> selectHotelFacility(String hotelCode);
	
}

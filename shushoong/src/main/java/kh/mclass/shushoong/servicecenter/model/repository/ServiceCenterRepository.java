package kh.mclass.shushoong.servicecenter.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;

@Mapper
public interface ServiceCenterRepository {
	//1:1 문의 검색
	public List<OnlineQnADto> selectAllList(String category, String keyword, String questCatCategory);
}

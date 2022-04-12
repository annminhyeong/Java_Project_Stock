package poly.service;

import java.util.List;

import poly.dto.NewsDTO;

public interface IMongoNewsService {
	
	//주식 뉴스 insert
	public int collectNewsRank() throws Exception;
	
	//주식 뉴스 select
	public List<NewsDTO>getNews() throws Exception;
}

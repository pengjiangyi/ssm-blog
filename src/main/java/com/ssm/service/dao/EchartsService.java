package com.ssm.service.dao;

import java.util.List;
import java.util.Map;

public interface EchartsService {

	List<Map> selectIp();

	List selectSearchKeywords();

	List<Map> selectSearchKeywordsAndNum();

	List<Map> selectArticleEcharts();

	List<Map> selectCommentsEcharts();

	List<Map> selectMessageEcharts();

	List<Map> selectIpMonthOfYear();

	List<Map> selectArticleEchartsMonthOfYear();

	List<Map> selectCommentsEchartsMonthOfYear();

	List<Map> selectMessageEchartsMonthOfYear();

}

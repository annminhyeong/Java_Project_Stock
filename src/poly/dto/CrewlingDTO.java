package poly.dto;

public class CrewlingDTO {
	private String stock_deal_date; //투자자별 매매동향 날짜
	private String stock_deal_data;//투자자별 거래량
	private String stock_deal_institution; //기관 순매매량
	private String stock_deal_foreign; //외국인 순매매량
	
	private String stock_code;
	private String stock_name;
	private String id;
	
	private String news_link;
	private String news_title;
	private String news_writer;
	private String news_time;
	private String news_contents;
	
	private String talk_date;
	private String talk_link;
	private String talk_title;
	private String talk_id;
	private String talk_contents;
	
	private String present_price; //현재가
	private String net_change;//전일대비
	private String price_change;//등락폭
	private String market_capitalization;//시가총액
	
	private String kosdate;
	private String kosdata;
	
	private String wordcount;
	private String wordname;

	private String rank;
	private String company_name;
	private String searchrate;
	
	private String stop_num;
	private String stop_name;
	private String stop_date;
	private String stop_cause;
	
	private String oil_name;
	private String oil_unit;
	private String oil_present_price;
	private String oil_price_change;
	private String oil_standard_date;
	
	private String theme_num;
	private String theme_name;
	private String theme_date;
	private String theme_ju1;
	private String theme_ju2;
	
	private String world_index;
	private String world_final;
	private String world_high;
	private String world_low;
	
		
	public String getStock_deal_date() {
		return stock_deal_date;
	}
	public void setStock_deal_date(String stock_deal_date) {
		this.stock_deal_date = stock_deal_date;
	}
	public String getStock_deal_data() {
		return stock_deal_data;
	}
	public void setStock_deal_data(String stock_deal_data) {
		this.stock_deal_data = stock_deal_data;
	}
	
	public String getStock_deal_institution() {
		return stock_deal_institution;
	}
	public void setStock_deal_institution(String stock_deal_institution) {
		this.stock_deal_institution = stock_deal_institution;
	}
	public String getStock_deal_foreign() {
		return stock_deal_foreign;
	}
	public void setStock_deal_foreign(String stock_deal_foreign) {
		this.stock_deal_foreign = stock_deal_foreign;
	}
	
	
	public String getStock_code() {
		return stock_code;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	public String getNews_link() {
		return news_link;
	}
	public void setNews_link(String news_link) {
		this.news_link = news_link;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_writer() {
		return news_writer;
	}
	public void setNews_writer(String news_writer) {
		this.news_writer = news_writer;
	}
	public String getNews_time() {
		return news_time;
	}
	public void setNews_time(String news_time) {
		this.news_time = news_time;
	}
	public String getNews_contents() {
		return news_contents;
	}
	public void setNews_contents(String news_contents) {
		this.news_contents = news_contents;
	}
	public String getTalk_date() {
		return talk_date;
	}
	public void setTalk_date(String talk_date) {
		this.talk_date = talk_date;
	}
	public String getTalk_link() {
		return talk_link;
	}
	public void setTalk_link(String talk_link) {
		this.talk_link = talk_link;
	}
	public String getTalk_title() {
		return talk_title;
	}
	public void setTalk_title(String talk_title) {
		this.talk_title = talk_title;
	}
	public String getTalk_id() {
		return talk_id;
	}
	public void setTalk_id(String talk_id) {
		this.talk_id = talk_id;
	}
	public String getTalk_contents() {
		return talk_contents;
	}
	public void setTalk_contents(String talk_contents) {
		this.talk_contents = talk_contents;
	}
	public String getPresent_price() {
		return present_price;
	}
	public void setPresent_price(String present_price) {
		this.present_price = present_price;
	}
	public String getNet_change() {
		return net_change;
	}
	public void setNet_change(String net_change) {
		this.net_change = net_change;
	}
	public String getPrice_change() {
		return price_change;
	}
	public void setPrice_change(String price_change) {
		this.price_change = price_change;
	}
	public String getMarket_capitalization() {
		return market_capitalization;
	}
	public void setMarket_capitalization(String market_capitalization) {
		this.market_capitalization = market_capitalization;
	}
	public String getKosdate() {
		return kosdate;
	}
	public void setKosdate(String kosdate) {
		this.kosdate = kosdate;
	}
	public String getKosdata() {
		return kosdata;
	}
	public void setKosdata(String kosdata) {
		this.kosdata = kosdata;
	}
	public String getWordcount() {
		return wordcount;
	}
	public void setWordcount(String wordcount) {
		this.wordcount = wordcount;
	}
	public String getWordname() {
		return wordname;
	}
	public void setWordname(String wordname) {
		this.wordname = wordname;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getSearchrate() {
		return searchrate;
	}
	public void setSearchrate(String searchrate) {
		this.searchrate = searchrate;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getStop_num() {
		return stop_num;
	}
	public void setStop_num(String stop_num) {
		this.stop_num = stop_num;
	}
	public String getStop_name() {
		return stop_name;
	}
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}
	public String getStop_date() {
		return stop_date;
	}
	public void setStop_date(String stop_date) {
		this.stop_date = stop_date;
	}
	public String getStop_cause() {
		return stop_cause;
	}
	public void setStop_cause(String stop_cause) {
		this.stop_cause = stop_cause;
	}
	public String getOil_name() {
		return oil_name;
	}
	public void setOil_name(String oil_name) {
		this.oil_name = oil_name;
	}
	public String getOil_unit() {
		return oil_unit;
	}
	public void setOil_unit(String oil_unit) {
		this.oil_unit = oil_unit;
	}
	public String getOil_present_price() {
		return oil_present_price;
	}
	public void setOil_present_price(String oil_present_price) {
		this.oil_present_price = oil_present_price;
	}
	public String getOil_price_change() {
		return oil_price_change;
	}
	public void setOil_price_change(String oil_price_change) {
		this.oil_price_change = oil_price_change;
	}
	public String getOil_standard_date() {
		return oil_standard_date;
	}
	public void setOil_standard_date(String oil_standard_date) {
		this.oil_standard_date = oil_standard_date;
	}
	public String getTheme_num() {
		return theme_num;
	}
	public void setTheme_num(String theme_num) {
		this.theme_num = theme_num;
	}
	public String getTheme_name() {
		return theme_name;
	}
	public void setTheme_name(String theme_name) {
		this.theme_name = theme_name;
	}
	public String getTheme_date() {
		return theme_date;
	}
	public void setTheme_date(String theme_date) {
		this.theme_date = theme_date;
	}
	public String getTheme_ju1() {
		return theme_ju1;
	}
	public void setTheme_ju1(String theme_ju1) {
		this.theme_ju1 = theme_ju1;
	}
	public String getTheme_ju2() {
		return theme_ju2;
	}
	public void setTheme_ju2(String theme_ju2) {
		this.theme_ju2 = theme_ju2;
	}
	public String getWorld_index() {
		return world_index;
	}
	public void setWorld_index(String world_index) {
		this.world_index = world_index;
	}
	public String getWorld_final() {
		return world_final;
	}
	public void setWorld_final(String world_final) {
		this.world_final = world_final;
	}
	public String getWorld_high() {
		return world_high;
	}
	public void setWorld_high(String world_high) {
		this.world_high = world_high;
	}
	public String getWorld_low() {
		return world_low;
	}
	public void setWorld_low(String world_low) {
		this.world_low = world_low;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}

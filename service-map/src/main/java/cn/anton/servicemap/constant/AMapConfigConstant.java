package cn.anton.servicemap.constant;

/*
 * https://restapi.amap.com/v3/direction/driving?origin=108.44,22.08&destination=108.36,22.85&extensions=all&output=json&key=3accf5f8347ba6031840a9158d194728
 * @author: Anton
 * @create_date: 2023/9/22 21:08
 */
public class AMapConfigConstant {
	
	/**
	 * 路径规划地址
	 */
	public static final String URL = "https://restapi.amap.com/v3/direction/driving";
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	public static final String EXTENSIONS = "extensions";
	public static final String EXTENSIONS_VALUE_ALL = "all";
	public static final String EXTENSIONS_VALUE_BASE = "base";
	
	
	public static final String OUTPUT = "output";
	public static final String OUTPUT_VALUE_JSON = "json";
	public static final String OUTPUT_VALUE_XML = "xml";
	
	public static final String KEY = "key";
	public static final String KEY_VALUE = "3accf5f8347ba6031840a9158d194728";
	
	/**
	 * 路径规划 JSON KEY值
	 */
	public static final String KEY_STATUS = "status";
	public static final String KEY_ROUTE = "route";
	public static final String KEY_PATHS = "paths";
	public static final String KEY_DISTANCE = "distance";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_STRATEGY = "strategy";
	public static final String KEY_TOLLS = "tolls";
	public static final String KEY_TOLL_DISTANCE = "toll_distance";
	public static final String KEY_TRAFFIC_LIGHTS = "traffic_lights";
	
	public static final String DISTRICTS = "districts";
	public static final String ADCODE = "adcode";
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String CITYCODE = "citycode";
	public static final String STREET = "street";
	
	/**
	 * 新建服务URL
	 */
	public static final String SERVICE_ADD_URL = "https://tsapi.amap.com/v1/track/service/add";
	
	/**
	 * 终端服务URL
	 */
	/**
	 * 创建终端
	 */
	public static final String TERMINAL_ADD_URL = "https://tsapi.amap.com/v1/track/terminal/add";
	/**
	 * 删除终端
	 */
	public static final String TERMINAL_DELETE_URL = "https://tsapi.amap.com/v1/track/terminal/delete";
	/**
	 * 修改终端
	 */
	public static final String TERMINAL_UPDATE_URL = "https://tsapi.amap.com/v1/track/terminal/update";
	/**
	 * 查询终端
	 */
	public static final String TERMINAL_LIST_URL = "https://tsapi.amap.com/v1/track/terminal/list";
	
	/**
	 * 创建轨迹URL
	 */
	public static final String TRACE_CREATE_URL = "https://tsapi.amap.com/v1/track/trace/add";
	
	/**
	 * 轨迹点上传
	 */
	public static final String POINT_UPDATE_URL = "https://tsapi.amap.com/v1/track/point/upload";
	
	/**
	 * 周边搜索
	 */
	public static final String TERMINAL_AROUNDSEARCH_URL = "https://tsapi.amap.com/v1/track/terminal/aroundsearch";
}

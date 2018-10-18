package nc.vo.ifm;

import java.util.HashMap;
import java.util.Map;

import nccloud.framework.web.ui.pattern.grid.Grid;

/**
 * 返回前端数据格式，在grid基础上增加单据状态页签数据，及动态添加其他数据
 * @author futao3
 * Aug 27, 2018
 */
public class TMGrid {
	/**
	 * 单据状态页签数据模型
	 */
	private Map<String, String> numvalues = new HashMap<String, String>();
	/**
	 * 拓展属性
	 */
	private Map<String, String> extParam = new HashMap<>();
	

	private Grid grid;

	public Map<String, String> getNumValues() {
		return this.numvalues;
	}

	public void setNumValues(Map<String, String> map) {
		this.numvalues = map;
	}

	public void addNumValues(String key, String num) {
		this.numvalues.put(key, num);
	}

	public void removeNumValuesByKey(String key) {
		if (this.numvalues.containsKey(key)) {
			this.numvalues.remove(key);
		}
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * 添加拓展属性
	 * 
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 */
	public void addExtParam(String key, String value) {
		this.extParam.put(key, value);
	}

	/**
	 * 获取拓展属性
	 * 
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 * @return
	 */
	public String getExtParamByKey(String key, String value) {
		return this.extParam.get(key);
	}
	
	public Map<String, String> getExtParam() {
		return extParam;
	}

	public void setExtParam(Map<String, String> extParam) {
		this.extParam = extParam;
	}
}

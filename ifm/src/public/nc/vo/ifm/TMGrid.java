package nc.vo.ifm;

import java.util.HashMap;
import java.util.Map;

import nccloud.framework.web.ui.pattern.grid.Grid;

/**
 * ����ǰ�����ݸ�ʽ����grid���������ӵ���״̬ҳǩ���ݣ�����̬�����������
 * @author futao3
 * Aug 27, 2018
 */
public class TMGrid {
	/**
	 * ����״̬ҳǩ����ģ��
	 */
	private Map<String, String> numvalues = new HashMap<String, String>();
	/**
	 * ��չ����
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
	 * �����չ����
	 * 
	 * @param key
	 *            ���Լ�
	 * @param value
	 *            ����ֵ
	 */
	public void addExtParam(String key, String value) {
		this.extParam.put(key, value);
	}

	/**
	 * ��ȡ��չ����
	 * 
	 * @param key
	 *            ���Լ�
	 * @param value
	 *            ����ֵ
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

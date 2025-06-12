package com.ssdult.eflowerShop.dao;
import java.util.List;
import com.ssdult.eflowerShop.entity.Flower;
public interface FlowerDao {
/**
	 *�����ʻ���Ϣ
	 */
public abstract int save (Flower flower);
/**
*ɾ��һ���ʻ���¼ 
 */
public abstract int del(Flower flower);
/**
*�����ʻ���Ϣ
*/
public abstract int update(Flower flower);
	/**
	 * ��ѯ�����ʻ���Ϣ
	 */
	public abstract List<Flower> getAllFlower();
	/**
	 * ������֪�ʻ�����Ϣ��ѯ�ʻ���Ϣ
	 */
	public abstract List<Flower> selectFlower(String sql, String[] param);
	/**
	 * ���ݴ��ݲ��������ʻ���Ϣ
	 */
	public abstract int updateFlower(String sql, Object[] param);
	
}

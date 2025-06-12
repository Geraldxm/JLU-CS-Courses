package com.ssdult.eflowerShop.dao;
import java.util.List;
import com.ssdult.eflowerShop.entity.Flower;
public interface FlowerDao {
/**
	 *保存鲜花信息
	 */
public abstract int save (Flower flower);
/**
*删除一条鲜花记录 
 */
public abstract int del(Flower flower);
/**
*更新鲜花信息
*/
public abstract int update(Flower flower);
	/**
	 * 查询所有鲜花信息
	 */
	public abstract List<Flower> getAllFlower();
	/**
	 * 根据已知鲜花的信息查询鲜花信息
	 */
	public abstract List<Flower> selectFlower(String sql, String[] param);
	/**
	 * 根据传递参数更新鲜花信息
	 */
	public abstract int updateFlower(String sql, Object[] param);
	
}

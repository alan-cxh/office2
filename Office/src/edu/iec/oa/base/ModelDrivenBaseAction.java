package edu.iec.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;



/**ModelDrivenBaseAction这是一个反射类
 * @author TaoJG
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T>{
	private static final long serialVersionUID = 1L;
	/**实现对ModelDriven的支持*/
	protected T model;
	public ModelDrivenBaseAction(){
		try {
			//通过反射获得model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			//通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public T getModel() {
		return model;
	}
}

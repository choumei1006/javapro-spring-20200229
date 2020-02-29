package javapro.spring.util;

import javapro.spring.Annotation.Autowired;
import javapro.spring.util.FileUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author:choumei
 * @date:2020/2/29 18:17
 * @Description:
 */
public class ComponentScanParser {
    //容器
    HashMap<String,Object> beans = new HashMap<>();

    public void config(String packagePath) throws Exception {
        //1、调用工具类获取所有的className
        FileUtil fu = new FileUtil();
        List<String> listClassName = new ArrayList<>();
        fu.readClassName(packagePath,listClassName);
        for(String tempClassName : listClassName){
            System.out.println(tempClassName);
            //2、创建对象，反射
            Class<?> clazz = Class.forName(tempClassName);
            //3、创建对象实例
            Object instance = clazz.getConstructor().newInstance();

            //4、加入容器中
            String beanName = fu.toFirstLowwer(fu.getBeanName(tempClassName));
            beans.put(beanName,instance);

        }
    }

    /**
     * 根据对象名获取对象实例
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        return beans.get(beanName);
    }

    /**
     * 依赖注入
     */
    public void inject() throws Exception {
        for (String beanName : beans.keySet()){
            //获取当前对象的类
            Object instance = beans.get(beanName);
            Class clazz = instance.getClass();
            //获取当前对象的所有属性
            Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields){
                //获取当前属性的类
                Class fClazz = f.getClass();
                //获取当前属性是否有Autowired注解
                Annotation a = fClazz.getDeclaredAnnotation(Autowired.class);
                if(null != a){
                    f.setAccessible(true);
                    f.set(instance,beans.get(f.getName()));
                }
            }

        }
    }

}

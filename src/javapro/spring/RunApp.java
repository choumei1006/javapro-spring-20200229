package javapro.spring;

import javapro.spring.Annotation.ComponentScan;
import javapro.spring.classes.Hello;
import javapro.spring.util.ComponentScanParser;

/**
 * @author:choumei
 * @date:2020/2/29 19:12
 * @Description:
 */
@ComponentScan("javapro.spring.classes")
public class RunApp {
    public static void main(String[] args) throws Exception {

        Class<?> clazz = RunApp.class;
        String dir = clazz.getResource("/").toString();;

        ComponentScan cs = clazz.getDeclaredAnnotation(ComponentScan.class);
        if(null != cs){
            String value = cs.value();
            dir += value.replaceAll("\\.","/");
            //从注解中获取这个路径
            String packagePath = dir;
            ComponentScanParser csp = new ComponentScanParser();
            csp.config(packagePath);

            csp.inject();   //依赖注入

            //获取一个对象
            Hello h = (Hello) csp.getBean("hello");
            h.hi();
        }



    }
}

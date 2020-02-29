package javapro.spring.util;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:choumei
 * @date:2020/2/29 17:13
 * @Description:  工具类
 */
public class FileUtil {
    /**
     * 工具类：首字母小写
     * @param name
     * @return
     */
    public String toFirstLowwer(String name){
        char first = name.charAt(0);
        first = Character.toLowerCase(first);
        return first+name.substring(1);
    }

    /**
     * 工具类:给一个class文件路径，转换为全类名：包名+类名
     * @param fileNamePath
     * @return
     */
    public String getClassName(String fileNamePath){

        Integer pos = fileNamePath.indexOf("bin");  //获取"bin"索引
        String s = fileNamePath.substring(pos+4);   //获取bin之后的路径
        s = s.substring(0,s.length()-6);   //删除后缀名
        s = s.replaceAll("\\\\","\\.");   // 将/转换为.
        return s;
    }

    /**
     * 工具类：把传入的全类名截取出类名，首字母小写
     * @param className
     * @return
     */
    public String getBeanName(String className){
        Integer pos = className.lastIndexOf(".");
        String s = className.substring(pos+1);
        return s;
    }

    /**
     * 工具类:遍历指定目录下的所有目录及文件
     * @param packagePath
     */
    public List<String> readClassName(String packagePath,List<String> list){
        //List<String> list = new ArrayList<>();
        File file = new File(packagePath);
        File[] files = file.listFiles();
        if(files.length > 0)
         {
            for(File f : files){
                if(f.isDirectory()){
                    //System.out.println("遍历子目录："+f.getAbsolutePath());
                    readClassName(f.getAbsolutePath(),list);
                }else{
                    //System.out.println(f.getAbsolutePath());
                    FileUtil fu = new FileUtil();
                    String className = fu.getClassName(f.getAbsolutePath()); //全路径
                    list.add(className);
                }
            }
        }
        return list;
    }

    @Test
    public void testRead(){
        FileUtil fu = new FileUtil();
        String packagePath = "D:\\Files\\IdeaProjects\\javapro-spring-20200229\\out";
        List<String> list = new ArrayList<>();
        fu.readClassName(packagePath,list);
        for(String s : list){
            System.out.println(s);
        }
    }


}

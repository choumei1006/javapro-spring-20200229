package javapro.spring.util;

import java.io.File;

/**
 * @author:choumei
 * @date:2020/2/29 17:26
 * @Description: 遍历目录，打印出多有文件
 */
public class RecursionDir {
    /**
     * 开发步骤：
     * 1、给定一个固定目录，利用文件对象提供的api转换；
     * 2、判断是否为空目录，如果有文件则遍历；
     * 3、判断是文件还是目录，如果是文件则输出控制台；
     * 4、如果是目录，调用自身（参数路径，当前目录），必须由出口；
     * 5、遍历完成；
     */
    public void readClassName(String dir){
        File file = new File(dir);
        File[] files = file.listFiles();
        if(null == files || files.length == 0){
            System.out.println("此目录为空！");
        }else{
            for(File f : files){
                if(f.isDirectory()){
                    System.out.println("遍历子目录："+f.getAbsolutePath());
                    readClassName(f.getAbsolutePath());
                }else{
                    System.out.println(f.getAbsolutePath());
                    FileUtil fu = new FileUtil();
                    String s = fu.getClassName(f.getAbsolutePath());
                    System.out.println(s);

                    String beanName = fu.getBeanName(s);
                    System.out.println(beanName);
                }
            }
        }
    }
}

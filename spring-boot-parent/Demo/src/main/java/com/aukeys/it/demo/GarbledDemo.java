package com.aukeys.it.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class GarbledDemo {
    public static void main(String[] args) throws Exception {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("e:\\aa.txt"),
                "UTF-8"));//读取记事本的文件，中文乱码直接这样写不行，要如下判断*/
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("e:\\aa.txt"),
                getCode("e:\\aa.txt")));
        String line=null;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }

    }
    // 获取编码格式 gb2312,UTF-16,UTF-8,Unicode,UTF-8
    public static String getCode(String path) throws Exception {
        InputStream inputStream = new FileInputStream(path);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "GBK"; // 或GB2312
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        else if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";
        inputStream.close();
        return code;
    }
}

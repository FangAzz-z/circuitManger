package com.xbky.circuitManger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PrintUtil {
    private static Logger logger = LoggerFactory.getLogger(PrintUtil.class);

    public static void print(String content) {
        try {
            String path0 = new File("html/test0.html").getAbsolutePath();
            String path = new File("html/test.html").getAbsolutePath();
            coverHtml(path0,path);
            String lbxPath = new File("html/wx2.lbx").getAbsolutePath().replaceAll("\\\\","/");
            modifyFileContent(path,"yyyyy", lbxPath);
            modifyFileContent(path,"xxxxx", content);
            String str = "cmd /cstart iexplore " + path;
            Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            logger.error("",e);
        }
    }

    private  static void coverHtml(String fromFileName,String toFileName){
        try {
            BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(fromFileName), StandardCharsets.UTF_8));
            String b="";
            StringBuffer sb = new StringBuffer();
            try {
                while((b = br.readLine())!=null){
                    //得到文件内容放到sb中
                    sb.append(b);
                    //这里可以写自己想对每一行的处理代码
                }
                br.close();
                String s = sb.toString();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(toFileName),StandardCharsets.UTF_8));
                bw.write(s);
                bw.flush();
                bw.close();
            } catch (IOException e) {
                logger.error("",e);
            }
        } catch (FileNotFoundException e) {
            logger.error("",e);
        }

    }
    private static boolean modifyFileContent(String fileName, String oldStr, String newStr) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
            String line = null;
            long lastPoint = 0; //记住上一次的偏移量
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if(line.contains(oldStr)){
                    String str=line.replace(oldStr, newStr);
                    raf.seek(lastPoint);
                    raf.writeUTF(str);
                }
                lastPoint = ponit;
            }

//            String temp = "";
//            int len = oldStr.length();
//            StringBuffer tempBuf = new StringBuffer();
//                File file = new File(fileName);
//                FileInputStream fis = new FileInputStream(file);
//                InputStreamReader isr = new InputStreamReader(fis);
//                BufferedReader br = new BufferedReader(isr);
//                StringBuffer buf = new StringBuffer();
//                // 替换所有匹配的字符串
//                for (temp = null; (temp = br.readLine()) != null; temp = null) {
//                    if (temp.indexOf(oldStr) != -1) {
//                        temp = temp.replace(oldStr, newStr);
//                    }
//                    buf.append(temp);
//                    buf.append(System.getProperty("line.separator"));
//                }
//
//                br.close();
//                FileOutputStream fos = new FileOutputStream(file);
//                PrintWriter pw = new PrintWriter(fos);
//                pw.write(buf.toString().toCharArray());
//                pw.flush();
//                pw.close();
        } catch (Exception e) {
            logger.error("",e);
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                logger.error("",e);
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        String path0 = "D:\\workspace\\study-project\\circuitManger\\html\\test0.html";
        String path = "D:\\workspace\\study-project\\circuitManger\\html\\test.html";
        coverHtml(path0,path);
        modifyFileContent(path,"yyyyy", "D:/Program Files/Brother bPAC3 SDK/Templates/wx2.lbx");
        modifyFileContent(path,"xxxxx", "WX210907-23432 维修完成");
        String str = "cmd /cstart iexplore " + path;
        Runtime.getRuntime().exec(str);
//      testMethod("WX210907-88888 维修完成","D:\\Program Files\\Brother bPAC3 SDK\\Templates\\wx2.lbx");
    }
}

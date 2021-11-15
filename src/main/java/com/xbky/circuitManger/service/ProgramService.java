package com.xbky.circuitManger.service;

import com.xbky.circuitManger.dao.ProgramDao;
import com.xbky.circuitManger.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramService {
    Logger logger = LoggerFactory.getLogger(ProgramService.class);


    ProgramDao dao = new ProgramDao();

    public int programRun(String programName) throws Exception {
        try {
            String command = dao.getLocationByName(programName);
            if(ObjectUtil.isNull(command)){
                throw new Exception(String.format("%s:路径还未配置",programName));
            }
            System.err.println("Execute command : " + command);
            Runtime runtime = Runtime.getRuntime();
            Process process = null;
            int size = command.lastIndexOf("/");
            if (size < 1) {
                size = command.lastIndexOf("\\");
            }
            String path = command.substring(0,size);
            if(command.endsWith(".bat")) {
                process = runtime.exec(String.format("cmd /c start %s", command), null, new File(path));
            }
            else{
                process = runtime.exec(command, null, new File(path));
            }
            return 1;
        } catch (Exception e) {
            logger.error("",e);
            throw new Exception(String.format("%s:路径配置异常",programName));
        }
    }

    public void toMiniScreen(){
/*        int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        if(width>1024){
            setScreenSize(" h1024 v768");
        }*/
    }

    public void toBigScreen(){
/*        int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        if(width<1300){
            setScreenSize(" h1366 v768");
        }*/
    }

    private void setScreenSize(String sizeStr){
        try {
            Runtime runtime = Runtime.getRuntime();
            String command = new File("screen/SETRES.EXE").getAbsolutePath();
            int size = command.lastIndexOf("/");
            if (size < 1) {
                size = command.lastIndexOf("\\");
            }
            String path = command.substring(0,size);
            runtime.exec(command+sizeStr, null, new File(path));
        } catch (Exception e) {
            logger.error("",e);
        }
    }

    public int modifySreen(){
        return 1;
    }

    public static void main(String[] args) throws IOException {
//        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("C:\\GETrans\\wPTU_801\\bin\\ptuProgram.exe",null,new File("C:\\GETrans\\wPTU_801\\bin"));
        int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        System.out.println("--------->"+width);
    }
}

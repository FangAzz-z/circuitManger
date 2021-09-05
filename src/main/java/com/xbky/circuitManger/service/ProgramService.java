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
                process = runtime.exec(command);
            }

/*            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder build = new StringBuilder();
            while ((line = br.readLine()) != null) {
                System.err.println(line);
                build.append(line);
            }*/
            return 1;
        } catch (Exception e) {
            logger.error("",e);
            throw new Exception(String.format("%s:路径配置异常",programName));
        }
    }
}
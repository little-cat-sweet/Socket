package UploadFileTest;

import java.io.*;

/**
 * @Author HongYun on 2022/12/27
 */

public class Util {

    public static byte[] transferToByteArray(InputStream inputStream) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] content = new byte[1024];
        int readLength = 0;
        while((readLength = inputStream.read(content)) != -1){
            outputStream.write(content, 0, readLength);
        }
        byte[] res = outputStream.toByteArray();
        outputStream.close();
        return res;
    }

    public static String streamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder res = new StringBuilder();
        String content;
        while((content = bufferedReader.readLine()) != null){
            res.append(content).append("\r\n");
        }
        return res.toString();
    }
}

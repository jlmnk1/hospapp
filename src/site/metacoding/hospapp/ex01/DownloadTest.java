package site.metacoding.hospapp.ex01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 1. 다운로드
public class DownloadTest {

    public static void main(String[] args) {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append("http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService");
        sbUrl.append("?serviceKey="); // 서비스 키
        sbUrl.append("wJmmW29e3AEUjwLioQR22CpmqS645ep4S8TSlqtSbEsxvnkZFoNe7YG1weEWQHYZ229eNLidnI2Yt5EZ3Stv7g==");
        sbUrl.append("&pageNo="); // 페이지 넘버
        sbUrl.append("1");
        sbUrl.append("&numOfRows="); // 페이지당 가져올 데이터 수
        sbUrl.append("10");
        sbUrl.append("&_type="); // 데이터 포맷은 JSON
        sbUrl.append("json");

        try {
            URL url = new URL(sbUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));

            StringBuffer sbDownload = new StringBuffer();
            while (true) {
                String input = br.readLine();
                if (input == null) {
                    break;
                }
                sbDownload.append(input);
            }
            System.out.println(sbDownload.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
